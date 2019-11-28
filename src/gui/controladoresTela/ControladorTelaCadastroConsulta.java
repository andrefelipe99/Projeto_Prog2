package gui.controladoresTela;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import controladores.Fachada;
import exceptions.ConsultaJaExisteException;
import exceptions.DadosInvalidosException;
import gui.tela.GerenciadorHospitalAPP;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public class ControladorTelaCadastroConsulta implements Initializable {

    @FXML
    private DatePicker data;
    @FXML
    private TextField textoID;
    @FXML
    private TextField textoDescricao;
    @FXML
    private TextField textoHora;
    @FXML
    private TextField textoMin;
    @FXML
    private TextField medicoBusca;
    @FXML
    private TableView<Medico> tabelaMedicos;
    @FXML
    private TableColumn<Medico, String> colunaNome;
    @FXML
    private TableColumn<Medico, String> colunaEspecialidade;

    private ObservableList<Medico> listaMedicos;
    private Fachada fachada = Fachada.getInstance();

    public void initialize(URL arg0, ResourceBundle arg1) {
        medicoBusca.setOnKeyReleased((KeyEvent e) -> {
            tabelaMedicos.getItems().setAll(busca());
        });
        carregarTableMedico();
    }

    @FXML
    void cadastrar() {
        int ID = -1;
        int hora = -1;
        int min = -1;
        LocalDate date = null;
        LocalTime time = null;
        String descricao = "";
        if (!textoID.getText().isEmpty() && textoID.getText().matches("^[0-9]*$")) {
            ID = Integer.parseInt(this.textoID.getText());
        }
        if (!textoHora.getText().isEmpty() && textoHora.getText().matches("^[0-9]*$")) {
            hora = Integer.parseInt(this.textoHora.getText());
        }
        if (!textoMin.getText().isEmpty() && textoMin.getText().matches("^[0-9]*$")) {
            min = Integer.parseInt(this.textoMin.getText());
        }
        if (hora < 0) {
            hora = 0;
        } else if (hora >= 24) {
            hora = 23;
        }
        if (min < 0) {
            min = 0;
        } else if (min >= 60) {
            min = 59;
        }
        time = LocalTime.of(hora, min);
        if (!textoDescricao.getText().isEmpty()) {
            descricao = this.textoDescricao.getText();
        }
        if (data.getValue() == null) {
            date = LocalDate.of(1900, 1, 1);
        } else {
            date = data.getValue();
        }
        if (date.isEqual(LocalDate.of(1900, 1, 1))) {
            time = LocalTime.of(0, 0);
        }
        try {
            Consulta consulta = new Consulta(ID, tabelaMedicos.getSelectionModel().getSelectedItem(), pacienteSelecionado(),
                     descricao, LocalDateTime.of(date, time), LocalDateTime.of(date, time.plusMinutes(20)));
            fachada.cadastrarConsulta(consulta);
            alertaConfirmacaoOK();
            voltar();
            fachada.salvarConsultas();
        } catch (DadosInvalidosException e) {
            e.erro();
        } catch (ConsultaJaExisteException e) {
            e.erro();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void voltar() throws IOException {
        GerenciadorHospitalAPP.getStage().close();
        GerenciadorHospitalAPP cons = new GerenciadorHospitalAPP();
        cons.start(new Stage(), "/gui/fxmlAtendente/TelaConsulta.fxml", "Historico do paciente");
    }

    public void alertaConfirmacaoOK() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Informacao");
        alerta.setHeaderText("Marcado com sucesso!");
        alerta.setContentText("Pressione 'OK' para retornar!");
        alerta.showAndWait();
    }

    public Paciente pacienteSelecionado() {
        Paciente pacienteSelecionado = null;

        try {
            BufferedReader leitor = new BufferedReader(new FileReader("src/dados/arquivos/pacienteSelecionado.txt"));

            if (leitor.ready()) {
                String cpf = leitor.readLine();
                pacienteSelecionado = fachada.buscarPaciente(cpf);
            }
            leitor.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return pacienteSelecionado;
    }

    public void carregarTableMedico() {
        colunaNome.setCellValueFactory((TableColumn.CellDataFeatures<Medico, String> m)
                -> new SimpleStringProperty(m.getValue().getNome()));
        colunaEspecialidade.setCellValueFactory((TableColumn.CellDataFeatures<Medico, String> m)
                -> new SimpleStringProperty(m.getValue().getArea()));
        listaMedicos = FXCollections.observableArrayList();
        tabelaMedicos.setItems(listaMedicos);
        listaMedicos.addAll(fachada.listarMedicos());

    }

    public void atualizarTabela() {
        tabelaMedicos.getItems().setAll(fachada.listarMedicos());
    }

    private ObservableList<Medico> busca() {
        atualizarTabela();
        ObservableList<Medico> medicoPesquisa = FXCollections.observableArrayList();
        for (int i = 0; i < listaMedicos.size(); i++) {
            if (listaMedicos.get(i).getNome().toLowerCase().contains(medicoBusca.getText().toLowerCase())
                    || listaMedicos.get(i).getArea().contains(medicoBusca.getText())) {
                medicoPesquisa.add(listaMedicos.get(i));
            }
        }
        return medicoPesquisa;
    }
}
