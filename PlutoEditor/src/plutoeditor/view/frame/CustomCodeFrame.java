package plutoeditor.view.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CustomCodeFrame extends JFrame {
	
	private String customCode;
	private JTextArea textArea;
	private JPanel buttonPanel;
	private JButton saveButton;
	private JButton cancelButton;

	public CustomCodeFrame(String customCode) {
		setTitle("Write Your Custom Code");
		
		this.customCode = customCode;
		
		setSize(600, 600);
		setLocationRelativeTo(null);
		
		textArea = new JTextArea(25, 40);
		textArea.setText(customCode);
		textArea.setSize(400, 400);
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
	    getContentPane().add(textArea, BorderLayout.NORTH);
	    
	    buttonPanel = new JPanel();
	    buttonPanel.setLayout(new FlowLayout());
	    
	    saveButton = new JButton("Save");
	    cancelButton = new JButton("Cancel");
	    
	    buttonPanel.add(saveButton);
	    buttonPanel.add(cancelButton);
	    
	    getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}

	private static final long serialVersionUID = 1L;

	public String getTextFromTextArea(){
		return this.textArea.getText();
	}
	
	public void setSaveButtonListener(ActionListener listener){
		this.saveButton.addActionListener(listener);
	}
	
	public void setCancelButtonListener(ActionListener listener){
		this.cancelButton.addActionListener(listener);
	}
}
