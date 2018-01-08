package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JComboBox;
public class SubwayUI implements VariablesUI{
	
	
	private JComboBox<String> squares = new JComboBox<String>(controllerInstance.getSquaresName());
	private int index = 0;
	
	public void getSubwayFrame() {
		createSubwayFrame();
	}
	
	public void createSubwayFrame() {
		subwayFrame.setVisible(true);
		subwayFrame.setLocation(400, 400);
		subwayFrame.setBackground(background);
		subwayFrame.setSize(400, 70);
		subwayFrame.setResizable(false);
		subwayPanel.setVisible(true);
		subwayPanel.setBackground(background);
		subwayPanel.setSize(400, 70);
		subwayFrame.add(subwayPanel);
		subwayPanel.add(squares);
		subwayPanel.add(letsGo);
		letsGo.setEnabled(true);
		squares.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) ((JComboBox<String>) e.getSource()).getSelectedItem();
				index = Arrays.asList(controllerInstance.getSquaresName()).indexOf(name);
			}
		});
	}
	
	public int getIndex() {
		return index;
	}
}
