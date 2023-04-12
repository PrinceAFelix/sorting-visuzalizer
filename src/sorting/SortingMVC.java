package sorting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;




public class SortingMVC extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 1080;
    private static final int HEIGHT = 555;
    private static final int BAR_WIDTH = 5;

	JButton bubbleSortBtn, insertionSortBtn, selectionSortBtn, mergeSortBtn, quickSortBtn, shuffleBtn;
	JPanel northPanel, centerPanel, southPanel;
	
	Controller myController = new Controller();
	
	private boolean isBubbleSort = false;
	private boolean isInsertionSort = false;
	private boolean isSelectionSort = false;
	private boolean isMergeSort = false;
	private boolean isQuickSort = false;
	private boolean isShuffle = false;
	

	private Random rand = new Random();
	
	private Rectangle2D[] rect = new Rectangle2D[1000 / BAR_WIDTH];

	private int []arr = new int[1000 / BAR_WIDTH];

	private JPanel mainFrame = new JPanel();

	private boolean isSort = false;
	
	Timer timer;
	
	private Color color = Color.RED;
	private int current = 0;

	
	//Sorting buttons
	SortingMVC(){
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(10, HEIGHT-55);
			
			System.out.println(arr[i]);
			
		}
		
		centerPanel = new JPanel();
		
		centerPanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		centerPanel.setBackground(Color.BLACK);
		
		timer = new Timer(1000, (ActionListener) this);
		timer.start();

		mainFrame.setLayout(new BorderLayout());
		mainFrame.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY)); 
		
		mainFrame.add(header(), BorderLayout.NORTH);
		mainFrame.add(centerPanel, BorderLayout.CENTER);
		mainFrame.add(footer(), BorderLayout.SOUTH);
		
		add(mainFrame);
		
	}
	
	public JPanel header() {
		northPanel = new JPanel();
		northPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		northPanel.setPreferredSize(new Dimension(1080,50));
		northPanel.setBackground(new Color(255, 130, 0, 100));
		
		bubbleSortBtn = new JButton("Bubble Sort");
		button(bubbleSortBtn);
		gbc.fill = GridBagConstraints.HORIZONTAL;  
	    gbc.gridx = 0;  
	    gbc.gridy = 0;  
	    northPanel.add(bubbleSortBtn, gbc);
		
		insertionSortBtn = new JButton("Insertion Sort");
		button(insertionSortBtn);
	    gbc.gridx = 1;  
	    gbc.gridy = 0;  
	    northPanel.add(insertionSortBtn, gbc);
		
		selectionSortBtn = new JButton("Selection Sort");
		button(selectionSortBtn);
		gbc.gridx = 2;  
	    gbc.gridy = 0;  
	    northPanel.add(selectionSortBtn, gbc);
		
		
		mergeSortBtn = new JButton("Merge Sort");
		button(mergeSortBtn);
		gbc.gridx = 3;  
	    gbc.gridy = 0;  
	    northPanel.add(mergeSortBtn, gbc);
	    
		
		quickSortBtn = new JButton("Quick Sort");
		button(quickSortBtn);
		gbc.gridx = 4;  
	    gbc.gridy = 0;  
	    northPanel.add(quickSortBtn, gbc);
	    
	    return northPanel;
	}
	
	
	
	public JPanel footer() {
		southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		southPanel.setPreferredSize(new Dimension(1080,50));
		southPanel.setBackground(new Color(255, 130, 0, 100));
		
	    shuffleBtn = new JButton("Shuffle");
	    button(shuffleBtn);
	    southPanel.add(shuffleBtn, BorderLayout.CENTER);
	    
	    return southPanel;
	}
	

	

	
	public JButton button (JButton myButton){
		
		//Apply settings for Button
		myButton.setBackground(Color.WHITE);
		myButton.setOpaque(true);
		myButton.setPreferredSize(new Dimension(100, 100));
		myButton.setMargin(new Insets(10, 10, 10, 10));
		
		
		myButton.addActionListener(myController);
	
		return myButton;
	}

	


	public void paint(Graphics g) {
		
		int x = 40;
		int y = 100;
		
		int x1 = this.getWidth() / 2;
        int y1 = this.getHeight() / 2;
		
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(Math.toRadians(180.0), x1, y1);
		
		
		
		for(int i = 0; i < rect.length; i++) {
			rect[i] = new Rectangle2D.Double(x, y, BAR_WIDTH, arr[i]);	
			x += 5;
			
			g.setColor(Color.WHITE);
			g2d.fill(rect[i]);
			if(isSort) {
				g.setColor(color);
				
				try {
					g2d.fill(rect[current]);
				}catch(Exception e) {
					System.out.println(current);
				}
				
				
				
			}
			
			
		}
		
		

		
		
	}
	
	
	
	
	public void showCurrent(int index) {
		
		try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		color = Color.RED;
		current = index;
		repaint();
	}
	
	
	public class Controller implements ActionListener {
		


		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == bubbleSortBtn)
				isBubbleSort = true;
			if(e.getSource() == insertionSortBtn)
				isInsertionSort = true;
			if(e.getSource() == selectionSortBtn)
				isSelectionSort = true;
			if(e.getSource() == mergeSortBtn)
				isMergeSort = true;
			if(e.getSource() == quickSortBtn)
				isQuickSort = true;
			if(e.getSource() == shuffleBtn)
				isShuffle = true;
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Thread animate = new Thread(new Runnable() {
			
			

			@Override
			public void run() {
				isSort = true;
				int n = arr.length;//Get the array length
				
				
				if(isBubbleSort) {
					for(int i = 0; i < n; i++) {
						
						for(int j = 0; j < n - i - 1; j++) {
							//Check if the array index 0 is greater than the array of next index
							if(arr[j] > arr[j+1]) {
								
	
								
								int temp = arr[j];//Set temp to the current array
								arr[j] = arr[j+1];//Switch the current array to the value of next array
								arr[j+1] = temp; //Finally, set the next array to the temp
								
								System.out.println("Switch: " + arr[j] + " and " + arr[j+1]);
								
								showCurrent(j);
								
								
							}

						}
					}
					isBubbleSort = false;
				}
				
				if(isInsertionSort) {
					for(int i = 1; i < n; i++) {
						int current = arr[i];//Represents the current array
						int j = i-1;
						//Switch the elements that are greater than the current array
						while((j >= 0) && (arr[j] > current)){
							arr [j+1] = arr[j];
							showCurrent(j);
							j--;
							
							
						}
						arr[j+1] = current;
					}
					isInsertionSort = false;
				}
				
				
				if(isSelectionSort) {
					for(int current = 0; current < n; current++) {
						
						int smallest = current; //start smallest at next
						for(int j = current + 1; j < n; j++) {
							//Check if array index j is less than the array index current
							if (arr[j] < arr[smallest])
								smallest = j; //Set smallest to J if so
						}
						
						int temp = arr[current]; //Set temp to the current array
						arr[current] = arr[smallest];//Switch the current array to the value of next array
						arr[smallest] = temp; //Finally, set the next array to the temp
						showCurrent(current);
					}
					isSelectionSort = false;
				}
				
				if(isMergeSort) {
					
					mergeSort(arr, 0, n-1);
					
					isMergeSort = false;
				}
				
				if(isQuickSort) {
					quickSort(arr, 0, n-1);
					isQuickSort = false;
				}
				
				if(isShuffle) {
					for(int i = 0; i < arr.length; i++) {
						arr[i] = rand.nextInt(100, 500);
						
					}
					repaint();
					
					isShuffle = false;
				}

				
			}
			
		});
		animate.start();
		
		
		
		
		
	}
	

	
	/**This method sort the array using merge
	 * 
	 * @param array represents the array of numbers
	 * @param left represents the left side of the array
	 * @param right represents the right side of the array
	 */
	public void mergeSort(int[] array, int left, int right) {
		
		//Check if the right side is less or equal than the left side
		if (right <= left) {
			return;
		}
			
		
		int mid = (left+right)/2;//Finds the midpoint
		//Calls recursively for both sides of mid point
		mergeSort(array, left, mid);
		mergeSort(array, mid+1, right);
		//Calls the merge method
		
		merge(array, left, mid, right);
		
		repaint();
		
	}
	
	/**This method merge two sub arrays
	 * 
	 * @param array represents the array of numbers
	 * @param left represents the left side of the array
	 * @param mid represents the midpoint of the array
	 * @param right represents the right side of the array
	 */
	public void merge(int[] array, int left, int mid, int right) {
		
		// calculating the two sides to be merge
		int lengthLeft = mid - left + 1;
		int lengthRight = right - mid;

		//Create the two temporary arrays
		int leftArray[] = new int [lengthLeft];
		int rightArray[] = new int [lengthRight];

		//Copy the arrays to the temp array
		for (int i = 0; i < lengthLeft; i++)
			leftArray[i] = array[left+i];
		for (int i = 0; i < lengthRight; i++)
			rightArray[i] = array[mid+i+1];

		//Index of first and second sub arrays
		int leftIndex = 0, rightIndex = 0;

		for (int i = left; i < right + 1; i++) {
		// if there are still uncopied elements in R and L, copy minimum of the two
			if (leftIndex < lengthLeft && rightIndex < lengthRight) {
				if (leftArray[leftIndex] < rightArray[rightIndex]) {
					array[i] = leftArray[leftIndex];
					leftIndex++;
				}else {
					array[i] = rightArray[rightIndex];
					rightIndex++;
				}
				
				
				
			}
			// if all the elements have been copied from rightArray, copy the rest of leftArray
			else if (leftIndex < lengthLeft) {
				array[i] = leftArray[leftIndex];
				leftIndex++;
				
				
			}
			// if all the elements have been copied from leftArray, copy the rest of rightArray
			else if (rightIndex < lengthRight) {
				array[i] = rightArray[rightIndex];
				rightIndex++;
				
				
			}
			
			
			
			
			
		}
		
		
	}
	
	/**This method sort the array using quick sort
	 * 
	 * @param array represents the array of numbers
	 * @param start represents the start index of the array
	 * @param end represents the lat index of the array
	 */
	public void quickSort(int[] array, int start, int end) {
		
		//Check if the right side is less or equal than the left side
		if (start >= end) {
			
			return;
		}
			
		
		//Set all the needed values
		int pivot = start, left = start + 1, right = end;
		
		while (left < right) {
			//Increment the left while it met the following
			while (array[left] < array[pivot] && left < right)
				left++;
			//Decrement the right while it met the following
			while (array[right] > array[pivot] && left < right)
				right--;
			
			//Swap the array
			int temp = array[left];
			array[left] = array[right];
			array[right] = temp;
			if (left < right) {
				left++;
				right--;
			}
			
			showCurrent(left);
			
		}
		
		if (array[left] > array[pivot]) {
			//Move the pivot and call recursively
			int temp = array[pivot];
			array[pivot] = array[left-1];
			array[left-1] = temp;
			quickSort(array, start, left-2);
			quickSort(array, left, end);
			
			
		}else {
			//Move the pivot and call recursively
			int temp = array[pivot];
			array[pivot] = array[left];
			array[left] = temp;
			quickSort(array, start, left-1);
			quickSort(array, left+1, end);
		
		}
		
		
		
		
	}

	
}







