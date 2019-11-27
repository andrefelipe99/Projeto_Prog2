package negocio;

import java.util.ArrayList;
import java.util.List;

public enum Especialidades {
    Cardiologista, Urologista, Ginecologista, Neurologista, Clinico_Geral, Nutricionista, Psicologo, Dermatologista, Endocrinologista,
    Oftalmologista, Otorrinolaringologista, Pediatra, Radiologista, Fisioterapeuta, Pneumologista, Ortopedista, Radioterapeuta, Quimioterapeuta,
    Nefrologista, Quiropraxista, Mastologista, Infectologista, Hematologista, Gerontologo, Reumatologista, Psiquiatra, Patologista;

    public static List<String> listarEspecialidades() {
        List<String> especialidades = new ArrayList<String>();

        Especialidades[] listadas = Especialidades.values();

        for (int i = 0; i < listadas.length; i++) {
            especialidades.add(listadas[i].name());
        }

        return especialidades;
    }

}
