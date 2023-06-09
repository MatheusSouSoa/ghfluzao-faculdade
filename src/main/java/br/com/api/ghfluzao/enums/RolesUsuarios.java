package br.com.api.ghfluzao.enums;

public enum RolesUsuarios {
    
    ADMIN(0), //GERENCIAR USUARIOS DO SISTEMA
    FUNC_INEP(1), //CADASTRAR, EDITAR E EXLUIR QUESTOES REFERENTE A CURSO, PROVA, QUESTAO, OPÇÃO, GABARITO, PARTE E ASSUNTO
    PROFESSOR(2), // AVALIAR SE AS OPÇÕES DO SEU CURSO/TEMA ESTAO DE ACORDO COM A QUESTAO A A SER INSERIDA
    USER(3); // APENAS BUSCAR INFORMAÇÕES 

    private int value;

    RolesUsuarios(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RolesUsuarios fromString(String status) {
        for (RolesUsuarios s : RolesUsuarios.values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }
}
