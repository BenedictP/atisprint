package atisprint;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JLabel;


public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JRadioButton printerRadioButton;
	private JButton printButton;
	private JTextPane txtpnStatus;
	
	private File document;
	private JLabel lblPassword;
	private JLabel lblAtisAnmeldenamen;
	private JLabel lblDrucker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton fileButton = new JButton("Chose File");
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filterDocs = new FileNameExtensionFilter("Documents", "pdf", "doc", "docx", "txt");
				fc.setFileFilter(filterDocs);				
				int returnValue = fc.showDialog(null, "Choose");
				if(returnValue == JFileChooser.APPROVE_OPTION)
		        {
		            document = fc.getSelectedFile();
		            txtpnStatus.setText("Print: " + document.getName());
		        }
			}
		});
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("424px:grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		contentPane.add(fileButton, "2, 2, fill, top");
		
		lblAtisAnmeldenamen = new JLabel("Atis Login (s_xxxx):");
		contentPane.add(lblAtisAnmeldenamen, "2, 4");
		
		usernameTextField = new JTextField();
		usernameTextField.setToolTipText("username");
		contentPane.add(usernameTextField, "2, 6, fill, default");
		usernameTextField.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		contentPane.add(lblPassword, "2, 8");
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("password");
		contentPane.add(passwordField, "2, 10, fill, default");
		
		lblDrucker = new JLabel("Printer:");
		contentPane.add(lblDrucker, "2, 12");
		
		printerRadioButton = new JRadioButton("sw2");
		printerRadioButton.setSelected(true);
		contentPane.add(printerRadioButton, "2, 14");
		
		printButton = new JButton("Print!");
		contentPane.add(printButton, "2, 16");
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Print print = new Print();
				char[] password = passwordField.getPassword();
				String pwd = String.valueOf(password);
			
				print.print(usernameTextField.getText(), pwd, document, txtpnStatus);
			}
		});
		
		txtpnStatus = new JTextPane();
		txtpnStatus.setText("Status");
		txtpnStatus.setEditable(false);
		contentPane.add(txtpnStatus, "2, 18, 1, 3, fill, fill");
	}

}
