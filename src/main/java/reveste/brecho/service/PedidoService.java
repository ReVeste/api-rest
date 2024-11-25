package reveste.brecho.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import reveste.brecho.dto.dashboards.DashboardMapper;
import reveste.brecho.dto.dashboards.LucrosMensaisDto;
import reveste.brecho.dto.pedido.CarrinhoDto;
import reveste.brecho.dto.pedido.PedidoAdicionarProdutoDto;
import reveste.brecho.dto.pedido.PedidoDto;
import reveste.brecho.dto.pedido.PedidoMapper;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.entity.Endereco;
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
import java.time.LocalDate;
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
    private final EnderecoService enderecoService;

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

        pedidoRepository.finalizarPedido(idPedido, StatusPedidoEnum.CONCLUIDO, LocalDate.now());

    }

    public void atualizarPedidoPago(int idPedido) {

        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);

        if (pedidoOpt.isEmpty()){
            throw new NaoEncontradaException("Pedido");
        }

        pedidoRepository.atualizarPedidoPago(idPedido, StatusPedidoEnum.PAGO, LocalDate.now());
        itemPedidoService.finalizarPedido(idPedido);

        idPedidosPagos.insert(idPedido);

    }

    public Pedido buscarPedidoParaEntrega() {

        if (idPedidosPagos.isEmpty()) {
            return null;
        }

        int idPedido = idPedidosPagos.peek();
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);

        if (pedidoOpt.isEmpty()){
            throw new NaoEncontradaException("Pedido");
        }

        return pedidoOpt.get();

    }

    public Endereco buscarEnderecoPedidoEntrega(Integer idUsuario) {

        List<Endereco> enderecos = enderecoService.listarPorUsuario(idUsuario);

        if (enderecos.isEmpty()) {return null;}

        return enderecos.get(0);

    }

    // Métodos relacionados as dtos das dashboards:

    public Double buscarLucroTotalMes() {
        LocalDate hoje = LocalDate.now();
        List<Pedido> pedidos = pedidoRepository.findAllByDataPagamentoBetweenAndStatus(
                PesquisaPeriodos.buscarInicioMes(hoje), PesquisaPeriodos.buscarFimMes(hoje),
                StatusPedidoEnum.CONCLUIDO);

        Double lucroTotal = 0.0;
        for (Pedido pedido : pedidos) {
            lucroTotal += pedido.getValorTotal();
        }
        return lucroTotal;
    }

    public Double buscarLucroTotalAno() {
        List<Pedido> pedidos = pedidoRepository.findAllByDataPagamentoBetweenAndStatus(
                PesquisaPeriodos.buscarInicioDoAno(), PesquisaPeriodos.buscarFimDoAno(), StatusPedidoEnum.CONCLUIDO);

        Double lucroTotal = 0.0;
        for (Pedido pedido : pedidos) {
            lucroTotal += pedido.getValorTotal();
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
                StatusPedidoEnum.CONCLUIDO);

        Double lucroTotalMesAnterior = 0.0;

        for (Pedido pedido : pedidosMesPassado) {
            lucroTotalMesAnterior += pedido.getValorTotal();
        }

        if (lucroTotalMesAtual > 0 && lucroTotalMesAnterior == 0) {
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

    public LucrosMensaisDto buscarLucrosMensais() {

        LocalDate hoje = LocalDate.now();
        LocalDate mesAnterior1 = hoje.minusMonths(1);
        LocalDate mesAnterior2 = hoje.minusMonths(2);
        LocalDate mesAnterior3 = hoje.minusMonths(3);
        LocalDate mesAnterior4 = hoje.minusMonths(4);
        LocalDate mesAnterior5 = hoje.minusMonths(5);

        List<Pedido> pedidosDoPeriodo = pedidoRepository.findAllByDataConclusaoBetweenAndStatus(
                PesquisaPeriodos.buscarInicioMes(mesAnterior5), PesquisaPeriodos.buscarFimMes(hoje),
                StatusPedidoEnum.CONCLUIDO);

        double lucroMesAtual = 0.0;
        double lucroMesAnterior1 = 0.0;
        double lucroMesAnterior2 = 0.0;
        double lucroMesAnterior3 = 0.0;
        double lucroMesAnterior4 = 0.0;
        double lucroMesAnterior5 = 0.0;

        for (Pedido pedido : pedidosDoPeriodo) {

            if ((pedido.getDataConclusao().isEqual(PesquisaPeriodos.buscarInicioMes(hoje)) ||
                    pedido.getDataConclusao().isAfter(PesquisaPeriodos.buscarInicioMes(hoje)))
                    &&
                    (pedido.getDataConclusao().isEqual(PesquisaPeriodos.buscarFimMes(hoje)) ||
                            pedido.getDataConclusao().isBefore(PesquisaPeriodos.buscarFimMes(hoje)))) {

                lucroMesAtual += pedido.getValorTotal();

            } else if ((pedido.getDataConclusao().isEqual(PesquisaPeriodos.buscarInicioMes(mesAnterior1)) ||
                    pedido.getDataConclusao().isAfter(PesquisaPeriodos.buscarInicioMes(mesAnterior1)))
                    &&
                    (pedido.getDataConclusao().isEqual(PesquisaPeriodos.buscarFimMes(mesAnterior1)) ||
                            pedido.getDataConclusao().isBefore(PesquisaPeriodos.buscarFimMes(mesAnterior1)))) {

                lucroMesAnterior1 += pedido.getValorTotal();

            } else if ((pedido.getDataConclusao().isEqual(PesquisaPeriodos.buscarInicioMes(mesAnterior2)) ||
                    pedido.getDataConclusao().isAfter(PesquisaPeriodos.buscarInicioMes(mesAnterior2)))
                    &&
                    (pedido.getDataConclusao().isEqual(PesquisaPeriodos.buscarFimMes(mesAnterior2)) ||
                            pedido.getDataConclusao().isBefore(PesquisaPeriodos.buscarFimMes(mesAnterior2)))) {

                lucroMesAnterior2 += pedido.getValorTotal();

            } else if ((pedido.getDataConclusao().isEqual(PesquisaPeriodos.buscarInicioMes(mesAnterior3)) ||
                    pedido.getDataConclusao().isAfter(PesquisaPeriodos.buscarInicioMes(mesAnterior3)))
                    &&
                    (pedido.getDataConclusao().isEqual(PesquisaPeriodos.buscarFimMes(mesAnterior3)) ||
                            pedido.getDataConclusao().isBefore(PesquisaPeriodos.buscarFimMes(mesAnterior3)))) {

                lucroMesAnterior3 += pedido.getValorTotal();

            } else if ((pedido.getDataConclusao().isEqual(PesquisaPeriodos.buscarInicioMes(mesAnterior4)) ||
                    pedido.getDataConclusao().isAfter(PesquisaPeriodos.buscarInicioMes(mesAnterior4)))
                    &&
                    (pedido.getDataConclusao().isEqual(PesquisaPeriodos.buscarFimMes(mesAnterior4)) ||
                            pedido.getDataConclusao().isBefore(PesquisaPeriodos.buscarFimMes(mesAnterior4)))) {

                lucroMesAnterior4 += pedido.getValorTotal();

            } else {
                lucroMesAnterior5 += pedido.getValorTotal();
            }
        }

        return DashboardMapper.toDetalheLucrosMensaisDto(lucroMesAtual, lucroMesAnterior1,
                lucroMesAnterior2, lucroMesAnterior3, lucroMesAnterior4,lucroMesAnterior5);

    }

}