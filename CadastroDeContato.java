import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroDeContato {
    private static DefaultListModel<String> modeloContatos;
    private static JList<String> listaContatos;
    private static JTextField campoNome;
    private static JTextField campoTelefone;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastro de Contatos");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Inicializa o modelo da lista e o componente JList para exibir contatos
        modeloContatos = new DefaultListModel<>();
        listaContatos = new JList<>(modeloContatos);

        // Painel para entrada dos dados
        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Campos de entrada para nome e telefone
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelEntrada.add(new JLabel("Nome:"), gbc);
        
        JPanel painelNome = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        campoNome = new JTextField(15); 
        painelNome.add(campoNome);
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelEntrada.add(painelNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelEntrada.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        campoTelefone = new JTextField(15);
        painelEntrada.add(campoTelefone, gbc);

        // Botões de adicionar, alterar e remover contato
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton botaoAdicionar = new JButton("Adicionar Contato");
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarContato(); // Adiciona um novo contato
            }
        });
        painelEntrada.add(botaoAdicionar, gbc);

        gbc.gridy = 3;
        JButton botaoAlterar = new JButton("Alterar Contato");
        botaoAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarContato(); // Altera o contato selecionado
            }
        });
        painelEntrada.add(botaoAlterar, gbc);

        gbc.gridy = 4;
        JButton botaoRemover = new JButton("Remover Contato");
        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerContato(); // Remove o contato selecionado
            }
        });
        painelEntrada.add(botaoRemover, gbc);

        
        frame.add(new JScrollPane(listaContatos), BorderLayout.CENTER);
        frame.add(painelEntrada, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void adicionarContato() {
        String nome = campoNome.getText().trim();
        String telefone = campoTelefone.getText().trim();
        if (!nome.isEmpty() && !telefone.isEmpty()) {
            modeloContatos.addElement("Nome: " + nome + ", Telefone: " + telefone);
            campoNome.setText("");
            campoTelefone.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void alterarContato() {
        int indiceSelecionado = listaContatos.getSelectedIndex();
        if (indiceSelecionado != -1) {
            String nome = campoNome.getText().trim();
            String telefone = campoTelefone.getText().trim();
            if (!nome.isEmpty() && !telefone.isEmpty()) {
                modeloContatos.set(indiceSelecionado, "Nome: " + nome + ", Telefone: " + telefone);
                campoNome.setText("");
                campoTelefone.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um contato para alterar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void removerContato() {
        int indiceSelecionado = listaContatos.getSelectedIndex();
        if (indiceSelecionado != -1) {
            modeloContatos.remove(indiceSelecionado);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um contato para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
