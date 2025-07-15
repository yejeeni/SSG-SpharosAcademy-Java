package springapp.gui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("springapp.gui") 
public class AppConfig {

	@Bean
	public JTextField name() {
		return new JTextField();
	}
	
	@Bean
	public JTextField email() {
		return new JTextField();
	}
	
	@Bean
	public JButton bt() {
		return new JButton("클릭");
	}
	
	
	@Bean
	public MyWin myWin(JComponent name, JComponent email, JComponent bt) {
		MyWin win = new MyWin(name, email, bt);
		
		return win;
	}
}
