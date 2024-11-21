package reveste.brecho.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import reveste.brecho.dto.pedido.CarrinhoDto;
import reveste.brecho.dto.pedido.PedidoAdicionarProdutoDto;
import reveste.brecho.dto.pedido.PedidoDto;
import reveste.brecho.dto.pedido.PedidoMapper;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Produto;
import reveste.brecho.entity.Usuario;
import reveste.brecho.enun.pedido.StatusPedidoEnum;
import reveste.brecho.exception.NaoEncontradaException;
import reveste.brecho.repository.PedidoRepository;
import reveste.brecho.service.usuario.UsuarioService;
import reveste.brecho.util.Escritor;
import reveste.brecho.util.PesquisaPeriodos;
import reveste.brecho.util.filaUtils.FilaObj;
import reveste.brecho.util.listaProduto.ListaProduto;
import reveste.brecho.util.listaProduto.ListaProdutoMapper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final ItemPedidoService itemPedidoService;
    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;

    FilaObj<Integer> idPedidosPagos = new FilaObj<>(200);

    @Modifying
    @Transactional
    public CarrinhoDto adicionarProduto(PedidoAdicionarProdutoDto pedidoDto) {

        Pedido pedido = pedidoRepository.findByUsuarioIdAndStatus(pedidoDto.getIdUsuario(), StatusPedidoEnum.EM_ANDAMENTO);

        if (pedido == null) {
            Usuario usuario = usuarioService.buscarPorId(pedidoDto.getIdUsuario());

            Pedido pedidoCriado = PedidoMapper.criarPedidoParaUsuario(usuario);
            pedidoRepository.save(pedidoCriado);

            itemPedidoService.adicionarProduto(
                    pedidoCriado,
                    pedidoDto.getIdProduto(),
                    pedidoDto.getQuantidadeProduto());

            ListaProduto listaProduto = ListaProdutoMapper.toListaProduto(listarProdutos(pedidoCriado.getId()));
            pedidoRepository.atualizarValorTotal(pedidoCriado.getId(), calcularValorTotal(listaProduto, 0));

            return PedidoMapper.toDetalheCarrinhoDto(
                    PedidoMapper.entidadeToPedidoDto(pedidoCriado), listarProdutos(pedidoCriado.getId())
            );
        }

        itemPedidoService.adicionarProduto(
                pedido,
                pedidoDto.getIdProduto(),
                pedidoDto.getQuantidadeProduto());

        ListaProduto listaProduto = ListaProdutoMapper.toListaProduto(listarProdutos(pedido.getId()));
        pedidoRepository.atualizarValorTotal(pedido.getId(), calcularValorTotal(listaProduto, 0));

        return PedidoMapper.toDetalheCarrinhoDto(
                PedidoMapper.entidadeToPedidoDto(pedido), listarProdutos(pedido.getId())
        );
    }

    public List<ProdutoDTO> listarProdutos(int pedidoId) {
        return itemPedidoService.buscaProdutoPorPedido(pedidoId);
    }

    public PedidoDto buscarPedido(int idPedido) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);

        if (pedidoOpt.isEmpty()) {
            throw new NaoEncontradaException("Usuário");
        }

        return PedidoMapper.entidadeToPedidoDto(pedidoOpt.get());
    }


    public List<ProdutoDTO> editarQuantidade(int idPedido, int idProduto, int quantidadeAtualizada) {
        ListaProduto listaProduto = ListaProdutoMapper.toListaProduto(listarProdutos(idPedido));
        pedidoRepository.atualizarValorTotal(idPedido, calcularValorTotal(listaProduto, 0));

        return itemPedidoService.editarQuantidadeProduto(idPedido, idProduto, quantidadeAtualizada);
    }

    public void removerProduto(int idPedido, int idProduto) {
        itemPedidoService.removerProdutoPedido(idPedido, idProduto);

        ListaProduto listaProduto = ListaProdutoMapper.toListaProduto(listarProdutos(idPedido));
        pedidoRepository.atualizarValorTotal(idPedido, calcularValorTotal(listaProduto, 0));
    }

    @Modifying
    @Transactional
    public void removerProdutos(int idPedido) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);

        if (pedidoOpt.isEmpty()){
            throw new NaoEncontradaException("Pedido");
        }

        itemPedidoService.removerProdutosPorPedido(idPedido);
        pedidoRepository.atualizarValorTotal(pedidoOpt.get().getId(), 0.0);
    }

    public void exportarPedidosEmAberto() {

        List<Pedido> pedidos = pedidoRepository.findAllByStatus(StatusPedidoEnum.PAGO);
        List<CarrinhoDto> carrinhoDtos = new ArrayList<>();

        if (pedidos.isEmpty()) throw new NaoEncontradaException("Pedido");

        for (Pedido pedido : pedidos) {
            List<ProdutoDTO> listaProdutos = listarProdutos(pedido.getId());
            carrinhoDtos.add(PedidoMapper.toDetalheCarrinhoDto(
                    PedidoMapper.entidadeToPedidoDto(pedido), listaProdutos));
        }

        Escritor.exportar(carrinhoDtos);
    }

    public static double calcularValorTotal(ListaProduto listaProduto, int index) {
        if (index == listaProduto.size()){
            return 0.0;
        }
        double subTotal = (listaProduto.exibirPorIndex(index).getQtdEstoque()
                * listaProduto.exibirPorIndex(index).getQtdEstoque());
        index++;
        return subTotal + calcularValorTotal(listaProduto, index);
    }

    public List<Pedido> listarPorStatus(Integer idUsuario, String status) {
        return status.isEmpty()
                ? pedidoRepository.findAll()
                : pedidoRepository.findAllByStatusAndUsuarioId(StatusPedidoEnum.valueOf(status), idUsuario);
    }

    public Integer buscarPedidoEmAberto(Integer idUsuario) {
        Optional<Integer> idPedido = pedidoRepository.findIdPedidoEmAbertoByUsuarioId(StatusPedidoEnum.EM_ANDAMENTO, idUsuario);

        if (idPedido.isEmpty()) {
            throw new NaoEncontradaException("Pedido");
        }

        return idPedido.get();
    }

    public void finalizarPedido() {

        if (idPedidosPagos.isEmpty()) {
            throw new NaoEncontradaException("idPedido");
        }

        int idPedido = idPedidosPagos.poll();

        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);

        if (pedidoOpt.isEmpty()){
            throw new NaoEncontradaException("Pedido");
        }

        pedidoRepository.finalizarPedido(idPedido, StatusPedidoEnum.CONCLUIDO);

    }

    public void atualizarPedidoPago(int idPedido) {

        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);

        if (pedidoOpt.isEmpty()){
            throw new NaoEncontradaException("Pedido");
        }

        // COLOCAR AQUI O UPDATE DO CAMPO private LocalDate dataPagamento; NO PEDIDO

        pedidoRepository.finalizarPedido(idPedido, StatusPedidoEnum.PAGO);
        itemPedidoService.finalizarPedido(idPedido);

        idPedidosPagos.insert(idPedido);

    }

    public Pedido buscarPedidoParaEntrega() {

        if (idPedidosPagos.isEmpty()) {
            return null;
        }

        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedidosPagos.peek());


        if (pedidoOpt.isEmpty()){
            throw new NaoEncontradaException("Pedido");
        }

        return pedidoOpt.get();

    }

    public Usuario buscarUsuarioPedidoEntrega(Pedido pedido) {
        return usuarioService.buscarPorId(pedido.getUsuario().getId());
    }

    // Métodos relacionados as dtos das dashboards:

    public Double buscarLucroTotalMes() {
        LocalDate hoje = LocalDate.now();
        List<Pedido> pedidos = pedidoRepository.findAllByDataPagamentoBetweenAndStatus(
                PesquisaPeriodos.buscarInicioMes(hoje), PesquisaPeriodos.buscarFimMes(hoje), StatusPedidoEnum.PAGO);

        Double lucroTotal = 0.0;
        for (int i = 0; i < pedidos.size(); i++) {
            lucroTotal += pedidos.get(i).getValorTotal();
        }
        return lucroTotal;
    }

    public Double buscarLucroTotalAno() {
        List<Pedido> pedidos = pedidoRepository.findAllByDataPagamentoBetweenAndStatus(
                PesquisaPeriodos.buscarInicioDoAno(), PesquisaPeriodos.buscarFimDoAno(), StatusPedidoEnum.PAGO);

        Double lucroTotal = 0.0;
        for (int i = 0; i < pedidos.size(); i++) {
            lucroTotal += pedidos.get(i).getValorTotal();
        }
        return lucroTotal;
    }

    public Integer buscarPedidosPagos() {
        if (pedidoRepository.findAllByStatus(StatusPedidoEnum.PAGO).isEmpty()) {return 0;}
        return pedidoRepository.findAllByStatus(StatusPedidoEnum.PAGO).size();
    }

    public Integer buscarProdutosDisponiveis() {
        if (produtoService.listarProdutosDisponiveis().isEmpty()) {return 0;}
        return produtoService.listarProdutosDisponiveis().size();
    }

    public Double buscarPorcentagemLucro() {

        Double lucroTotalMesAtual = buscarLucroTotalMes();
        LocalDate hoje = LocalDate.now();

        List<Pedido> pedidosMesPassado = pedidoRepository.findAllByDataPagamentoBetweenAndStatus(
                PesquisaPeriodos.buscarInicioMesAnterior(hoje), PesquisaPeriodos.buscarFimMesAnterior(hoje),
                StatusPedidoEnum.PAGO);

        Double lucroTotalMesAnterior = 0.0;

        for (Pedido pedido : pedidosMesPassado) {
            lucroTotalMesAnterior += pedido.getValorTotal();
        }

        if (lucroTotalMesAtual == 0 && lucroTotalMesAnterior > 0) {
            return 100.0;
        }

        if (lucroTotalMesAtual == 0 && lucroTotalMesAnterior == 0) {
            return 0.0;
        }

        return ((lucroTotalMesAnterior - lucroTotalMesAtual) / lucroTotalMesAtual) * 100;
    }

    public Integer buscarQtdProdutosEnviadosSemana() {

        LocalDate hoje = LocalDate.now();

        List<Pedido> pedidos = pedidoRepository.findAllByDataConclusaoBetweenAndStatus(
                PesquisaPeriodos.buscarInicioSemana(hoje), PesquisaPeriodos.buscarFimSemana(hoje),
                StatusPedidoEnum.CONCLUIDO);


        List<Produto> produtos = itemPedidoService.buscarProdutosRelacionados(pedidos);


        return produtos.size();
    }

    public Integer buscarQtdProdutosEnviadosMes() {

        LocalDate hoje = LocalDate.now();

        List<Pedido> pedidos = pedidoRepository.findAllByDataConclusaoBetweenAndStatus(
                PesquisaPeriodos.buscarInicioMes(hoje), PesquisaPeriodos.buscarFimMes(hoje),
                StatusPedidoEnum.CONCLUIDO);


        List<Produto> produtos = itemPedidoService.buscarProdutosRelacionados(pedidos);


        return produtos.size();
    }

    public Integer buscarQtdProdutosCadastradosSemana() {
        LocalDate hoje = LocalDate.now();
        return produtoService.buscarQtdProdutosCadastradosNoPeriodo(
                PesquisaPeriodos.buscarInicioSemana(hoje), PesquisaPeriodos.buscarFimSemana(hoje));
    }

    public Integer buscarQtdProdutosCadastradosMes() {
        LocalDate hoje = LocalDate.now();
        return produtoService.buscarQtdProdutosCadastradosNoPeriodo(
                PesquisaPeriodos.buscarInicioMes(hoje), PesquisaPeriodos.buscarFimMes(hoje));
    }

}