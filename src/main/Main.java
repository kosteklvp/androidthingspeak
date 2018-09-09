package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;


public class Main extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField tfTemperatura;
	private JFormattedTextField tfWilgotnosc;
	private JLabel lblTemparatura;
	private JLabel lblWilgotnosc;
	private NumberFormat amountFormat;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
					System.out.println("gotowe");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setTitle("Thingspeak App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 400, 272, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		lblTemparatura = new JLabel("Temperatura(\u00B0C)");
		
		lblWilgotnosc = new JLabel("Wilgotno\u015B\u0107(%)");
		
		amountFormat = NumberFormat.getNumberInstance();
		amountFormat.setMinimumIntegerDigits(1);
		amountFormat.setMaximumIntegerDigits(3);
		amountFormat.setMaximumFractionDigits(0);
		//amountFormat.setParseIntegerOnly(true);
		
		tfTemperatura = new JFormattedTextField(amountFormat);
		tfTemperatura.setColumns(10);
		
		tfWilgotnosc = new JFormattedTextField(amountFormat);
		tfWilgotnosc.setColumns(10);
		
		
		
		
		
		JButton btnNewButton = new JButton("Wy\u015Blij dane");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tfTemperatura.getText().trim().isEmpty() || tfWilgotnosc.getText().trim().isEmpty()) {
					System.out.println("Proszê wype³niæ oba pola");
					Error error = new Error("Pola zosta³y wype³nione nieprawid³owo");
				} else {
					int temperatura, wilgotnosc;
					temperatura = Integer.parseInt(tfTemperatura.getText().trim());
					wilgotnosc = Integer.parseInt(tfWilgotnosc.getText().trim());
					
					connectTo("CVTZBYR7ZWSMP1UP", temperatura + "", wilgotnosc + "");
					Error error = new Error("Dane wys³ane");
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTemparatura)
						.addComponent(lblWilgotnosc))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(tfTemperatura, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(tfWilgotnosc, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTemparatura)
						.addComponent(tfTemperatura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWilgotnosc)
						.addComponent(tfWilgotnosc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setVisible(true);
	}
	
	private static HttpURLConnection con;


	public static void connectTo(String apiKey,String temperatura, String wilgotnosc) {
		
        StringBuilder url = new StringBuilder();
        url.append("https://api.thingspeak.com/update.json?api_key=" + apiKey);
        url.append("&field1=" + temperatura);
        url.append("&field2=" + wilgotnosc);

        try {

            URL myurl = new URL(url.toString());
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());

        } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            
            con.disconnect();
        }
	}
}
