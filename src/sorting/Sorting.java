package sorting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Sorting {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable(){
			
			
		       @Override
		       public void run(){ 	
		    	
		    	JPanel sort = new SortingMVC();
		        JFrame frame = new JFrame();
//		        frame.setLayout(new BorderLayout());
		        frame.setTitle("Sorting Visualizer");
		        frame.setMinimumSize(new Dimension(1080, 700));
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setLocationByPlatform(true);
		        frame.pack();
		        frame.setVisible(true);  
		        frame.setResizable(false);
		        frame.setLocationRelativeTo(null);//screen center
		        
		        frame.add(sort);
		       
		       }
		});

	}

}
