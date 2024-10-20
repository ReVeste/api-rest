package reveste.brecho.util;

public class CepUtils {

    public static String validarEFormatarCep(String cep) {
        if (cep == null || cep.isEmpty()) {
            throw new IllegalArgumentException("CEP não pode ser nulo ou vazio.");
        }

        // Remover todos os caracteres que não sejam dígitos
        String cepNumeros = cep.replaceAll("\\D", ""); // Remove tudo que não é número

        // Verificar se tem 8 dígitos após a remoção dos caracteres indesejados
        if (cepNumeros.length() != 8) {
            throw new IllegalArgumentException("CEP inválido. O CEP deve conter 8 dígitos.");
        }

        // Formatar o CEP para o formato 00000-000
        return cepNumeros.replaceFirst("(\\d{5})(\\d{3})", "$1-$2");
    }
}
