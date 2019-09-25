import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Ex1GUIClass {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ex1GUIClass window = new Ex1GUIClass();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ex1GUIClass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("Courier New", Font.PLAIN, 12));
		frame.setBounds(100, 100, 448, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Global alignment");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		rdbtnNewRadioButton.setBounds(54, 182, 151, 21);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnEndSpaceFree = new JRadioButton("End space free alignment");
		rdbtnEndSpaceFree.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnEndSpaceFree.setBounds(54, 205, 260, 21);
		frame.getContentPane().add(rdbtnEndSpaceFree);
		
		JRadioButton rdbtnLacolAlignment = new JRadioButton("Local alignment");
		rdbtnLacolAlignment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnLacolAlignment.setBounds(54, 228, 188, 21);
		frame.getContentPane().add(rdbtnLacolAlignment);
		
		JRadioButton rdbtnAffireGapPenalty = new JRadioButton("Alignment with Affine Gap Penalty");
		rdbtnAffireGapPenalty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnAffireGapPenalty.setBounds(54, 251, 290, 21);
		frame.getContentPane().add(rdbtnAffireGapPenalty);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnEndSpaceFree);
		group.add(rdbtnLacolAlignment);
		group.add(rdbtnAffireGapPenalty);
		
		JLabel lblMatch = new JLabel("match");
		lblMatch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMatch.setBounds(32, 292, 54, 13);
		frame.getContentPane().add(lblMatch);
		
		textField = new JTextField();
		textField.setBounds(80, 291, 45, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSub = new JLabel("sub");
		lblSub.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSub.setBounds(159, 292, 37, 13);
		frame.getContentPane().add(lblSub);
		
		textField_1 = new JTextField();
		textField_1.setBounds(188, 291, 45, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
	
		
		JLabel lblIndle = new JLabel("indle");
		lblIndle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIndle.setBounds(266, 278, 48, 41);
		frame.getContentPane().add(lblIndle);
		
		textField_2 = new JTextField();
		textField_2.setBounds(299, 291, 45, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		JButton btnStart = new JButton("start");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(2);
				if ((rdbtnNewRadioButton.isSelected())&&!(textField_3.getText().equals(""))&&
					!(textField_4.getText().equals(""))&&!(textField_1.getText().equals(""))&&
					!(textField.getText().equals(""))&&!(textField_2.getText().equals(""))) {
					GlobalAlignment ga = new GlobalAlignment(textField_3.getText(),textField_4.getText(),textField.getText(),textField_1.getText(),textField_2.getText());
					ga.MakeMatrix();
					ga.FullMatrix();
					ga.printMatrix();
					ga.makePath();
					double score=ga.calcScore();
					int matchCount=ga.calcMatch();
					int subCount=ga.calcSub();
					int indleCount=ga.calcIndle();
					int gapCount=ga.calcGap();
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Courier New", Font.BOLD, 18)));       
					JOptionPane.showMessageDialog(null,"s= "+ga.s+"\n"+"   "+ga.res+"\n"+
							"t= "+ga.t+"\n\n\n"+"#score="+df.format(score)+"\n"+
							"#match= "+matchCount+"\n"+"#sub= "+subCount+"\n"+
							"#indle= "+indleCount+"\n"+"#gap= "+gapCount);
				}
				if ((rdbtnEndSpaceFree.isSelected())&&!(textField_3.getText().equals(""))&&
						!(textField_4.getText().equals(""))&&!(textField_1.getText().equals(""))&&
						!(textField_2.getText().equals(""))&&!(textField.getText().equals(""))) {
					EndSpaceFreeAlignment esfa = new EndSpaceFreeAlignment(textField_3.getText(),textField_4.getText(),textField.getText(),textField_1.getText(),textField_2.getText());
					esfa.MakeMatrix();	
					esfa.FullMatrix();
					esfa.printMatrix();
					esfa.makePath();
					double score=esfa.calcScore();
					int matchCount=esfa.calcMatch();
					int subCount=esfa.calcSub();
					int indleCount=esfa.calcIndle();
					int gapCount=esfa.calcGap();
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Courier New", Font.BOLD, 18)));       
					JOptionPane.showMessageDialog(null,"s= "+esfa.s+"\n"+"   "+esfa.res+"\n"+
							"t= "+esfa.t+"\n\n\n"+"#score="+df.format(score)+"\n"+
							"#match= "+matchCount+"\n"+"#sub= "+subCount+"\n"+
							"#indle= "+indleCount+"\n"+"#gap= "+gapCount);
				}
				if ((rdbtnLacolAlignment.isSelected())&&!(textField_3.getText().equals(""))&&
						!(textField_4.getText().equals(""))&&!(textField_1.getText().equals(""))&&
						!(textField_2.getText().equals(""))&&!(textField.getText().equals(""))) {
					LocalAlignment la = new LocalAlignment(textField_3.getText(),textField_4.getText(),textField.getText(),textField_1.getText(),textField_2.getText());
					la.MakeMatrix();	
					la.FullMatrix();
					la.printMatrix();
					la.makePath();
					double score=la.calcScore();
					int matchCount=la.calcMatch();
					int subCount=la.calcSub();
					int indleCount=la.calcIndle();
					int gapCount=la.calcGap();
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Courier New", Font.BOLD, 18)));       
					JOptionPane.showMessageDialog(null,"s= "+la.s+"\n"+"   "+la.res+"\n"+
							"t= "+la.t+"\n\n\n"+"#score="+df.format(score)+"\n"+
							"#match= "+matchCount+"\n"+"#sub= "+subCount+"\n"+
							"#indle= "+indleCount+"\n"+"#gap= "+gapCount);
							
				}
				if ((rdbtnAffireGapPenalty.isSelected())&&!(textField_3.getText().equals(""))&&
						!(textField_4.getText().equals(""))&&!(textField_1.getText().equals(""))&&
						!(textField.getText().equals(""))&&
						!(textField_5.getText().equals(""))&&!(textField_6.getText().equals(""))) {
					AlignmentWithAffineGapPenalty awagp = new AlignmentWithAffineGapPenalty(textField_3.getText(),
								textField_4.getText(), textField.getText(),textField_1.getText(),
								textField_5.getText(),textField_6.getText());
					awagp.MakeMatrix();
					awagp.FullMatrix();
					awagp.printMatrix();
					awagp.makePath();
					double score=awagp.calcScore();
					int matchCount=awagp.calcMatch();
					int subCount=awagp.calcSub();
					int indleCount=awagp.calcIndle();
					int gapCount=awagp.calcGap();
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Courier New", Font.BOLD, 18)));       
					JOptionPane.showMessageDialog(null,"s= "+awagp.s+"\n"+"   "+awagp.res+"\n"+
							"t= "+awagp.t+"\n\n\n"+"#score="+df.format(score)+"\n"+
							"#match= "+matchCount+"\n"+"#sub= "+subCount+"\n"+
							"#indle= "+indleCount+"\n"+"#gap= "+gapCount);
							
					
				}
			}

			
		});
		btnStart.setBounds(151, 373, 85, 21);
		frame.getContentPane().add(btnStart);
		
		textField_3 = new JTextField();
		textField_3.setBounds(114, 64, 219, 47);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(114, 121, 219, 47);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(80, 326, 45, 19);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(188, 326, 45, 19);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblWg = new JLabel("W_g");
		lblWg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWg.setBounds(41, 316, 45, 35);
		frame.getContentPane().add(lblWg);
		
		JLabel lblWs = new JLabel("W_s");
		lblWs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWs.setBounds(151, 327, 43, 18);
		frame.getContentPane().add(lblWs);
		
		JLabel lblNewLabel = new JLabel("\u05DB\u05DC \u05D4\u05D6\u05DB\u05D5\u05D9\u05D5\u05EA \u05E9\u05DE\u05D5\u05E8\u05D5\u05EA \u05DC\u05DE\u05E9\u05D4 \u05DC\u05D9\u05E4\u05DC\u05E1 \u05D5\u05E9\u05E7\u05D3 \u05D0\u05E1\u05D8\u05DE\u05E7\u05E8");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(42, 437, 350, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblText = new JLabel("sequence1:");
		lblText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblText.setBounds(32, 64, 119, 47);
		frame.getContentPane().add(lblText);
		
		JLabel lblText_1 = new JLabel("sequence2:");
		lblText_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblText_1.setBounds(32, 127, 119, 41);
		frame.getContentPane().add(lblText_1);
		
		JLabel label = new JLabel("\u05E2\u05D1\u05D5\u05D3\u05D4 1 \u05D1\u05D2\u05E0\u05D5\u05DE\u05D9\u05E7\u05D4 \u05D7\u05D9\u05E9\u05D5\u05D1\u05D9\u05EA");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(107, 13, 268, 32);
		frame.getContentPane().add(label);
		
		
	}
}
