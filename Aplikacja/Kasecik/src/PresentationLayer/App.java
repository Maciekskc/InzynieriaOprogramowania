package PresentationLayer;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Vector;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import BusinesLayer.Controller;
import DataLayer.Components.Rental;
import DataLayer.Components.Video;
import PresentationLayer.UsersUseCases.IModifyMethods;
import PresentationLayer.UsersUseCases.ISalesmanMethods;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class App extends JPanel implements IModifyMethods, ISalesmanMethods {

	/**
	 * Create the panel.
	 */
	String data[][] = { { "Vinod", "100" }, { "Raju", "200" }, { "Ranju", "300" } };
	String col[] = { "Name", "code" };
	private DefaultTableModel tableModel = new DefaultTableModel(data, col);
	private JTable table;

	private Controller controller = new Controller();

	public static void main(String[] args) {
		//do wyjebania
		Calendar cal = Calendar.getInstance();
		Calendar calen = Calendar.getInstance();
		boolean after = calen.after(cal);
		System.out.println("dawniej odjac wczesniej:"+ after);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new App();
			}
		});
		System.out.println("Duuuupa, ale ruszy�em");
	}

	public App() {
		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Kasecik");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().add(this);
		mainFrame.setSize(465, 350);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);

		setLayout(null);

		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 48, 430, 176);
		this.add(scrollPane);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 22);
		add(menuBar);

		JMenu mnWypoyczenia = new JMenu("Wypo\u017Cyczenia");
		menuBar.add(mnWypoyczenia);

		JMenuItem mntmAddRental = new JMenuItem("Dodaj");
		mnWypoyczenia.add(mntmAddRental);

		JMenuItem mntmDeleteRental = new JMenuItem("Usun");
		mnWypoyczenia.add(mntmDeleteRental);

		JMenuItem mntmEditRental = new JMenuItem("Edytuj");
		mnWypoyczenia.add(mntmEditRental);

		JMenu mnKasety = new JMenu("Kasety");
		menuBar.add(mnKasety);

		JMenuItem mntmAddVideo = new JMenuItem("Dodaj");
		mnKasety.add(mntmAddVideo);

		JMenuItem mntmDeleteVideo = new JMenuItem("Usun");
		mnKasety.add(mntmDeleteVideo);

		JMenuItem mntmEditVideo = new JMenuItem("Edytuj");
		mnKasety.add(mntmEditVideo);

		JButton buttonRentals = new JButton("Wypo\u017Cyczenia");
		buttonRentals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillWithRentals();
			}
		});
		buttonRentals.setBounds(10, 235, 199, 23);
		add(buttonRentals);

		JButton buttonVideos = new JButton("Filmy");
		buttonVideos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillWithVideos();
			}
		});
		buttonVideos.setBounds(240, 235, 199, 23);
		add(buttonVideos);
	}

	private void fillWithRentals() {
		String collumnNames[] = { "Imie", "Nazwisko", "Data wypo�yczenia", "Data zwrotu" };

		try {

			for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
				tableModel.removeRow(i);
			}
		} catch (NullPointerException e1) {
			System.out.println("NULL-POINTER: podczas wczytywania danych do tabeli");
		}

		tableModel.setColumnIdentifiers(collumnNames);
		try {
			for (int i = 0; i < controller.getDtoData().getAllRentals().size(); i++) {
				Rental rent = controller.getDtoData().getAllRentals().get(i);
				Object[] show = { rent.getCustomer().getName(), rent.getCustomer().getSurname(), rent.getRentalDate(),
						rent.getRentalExpireDate() };
				tableModel.insertRow(i, show);
			}
		} catch (Exception e1) {
			System.out.println(tableModel);
		}
	}

	private void fillWithVideos() {

		controller.getDtoData().addVideo(new Video(2354, "Wojny Klon�w", "Fantasy", 120, 2));
		String collumnNames[] = { "Id", "Nazwa", "Czas trwania", "Typ", "Ilo�� na stanie" };

		try {

			for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
				tableModel.removeRow(i);
			}
		} catch (NullPointerException e1) {
			System.out.println("NULL-POINTER: podczas wczytywania danych do tabeli");
		}

		tableModel.setColumnIdentifiers(collumnNames);
		try {
			for (int i = 0; i < controller.getDtoData().getAllVideos().size(); i++) {
				Video video = controller.getDtoData().getAllVideos().get(i);
				Object[] show = { video.getId(), video.getName(), video.getDuration() + "minut", video.getType(),
						video.getAmount() };
				tableModel.insertRow(i, show);
			}
		} catch (Exception e1) {
			System.out.println(tableModel);
		}
	}

}
