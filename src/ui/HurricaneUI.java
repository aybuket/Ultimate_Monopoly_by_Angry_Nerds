package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class HurricaneUI implements VariablesUI{
	JComboBox<String> colors;

	private String name = "";

	public void getHurricaneFrame() {
		colors = new JComboBox<String>(controllerInstance.getColoredBuiltAsset());
		hurricaneCard();
	}

	private void hurricaneCard() {
		hurricaneFrame.setVisible(true);
		hurricaneFrame.setLocation(400, 400);
		hurricaneFrame.setBackground(background);
		hurricaneFrame.setSize(400, 70);
		hurricaneFrame.setResizable(false);
		hurricanePanel.setVisible(true);
		hurricanePanel.setBackground(background);
		hurricanePanel.setSize(400, 70);
		hurricaneFrame.add(hurricanePanel);
		hurricanePanel.add(colors);
		hurricanePanel.add(destroy);
		destroy.setEnabled(true);
		colors.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name = (String) ((JComboBox<String>) e.getSource()).getSelectedItem();
				if(name==null) {
					name = controllerInstance.getColoredBuiltAsset()[0];
				}
				System.out.println(name);
			}
		});
	}

	public String getColor() {
		return name;
	}
}
