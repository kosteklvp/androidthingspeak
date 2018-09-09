package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Error extends JDialog {

	public Error(String msg) {
		setTitle("Wysy³anie danych");
		setResizable(false);
		setBounds(605, 425, 262, 133);
		
		JLabel lblPolaZostanyWypenione = new JLabel(msg);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblPolaZostanyWypenione, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addGap(38))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(98)
					.addComponent(btnOk, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(111))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblPolaZostanyWypenione, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOk)
					.addGap(25))
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
}
