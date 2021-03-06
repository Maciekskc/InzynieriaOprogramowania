package PresentationLayer;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	//do wyjebania
	String data[][] = { { "Maciej Bia�kowski", "Aplikacja do obs�ugi" }, { "Aleksandra Malicka", "wypo�yczalni kaset video" } };
	String col[] = { "Autorzy Projektu","Opis Projektu" };
	private DefaultTableModel tableModel = new DefaultTableModel(data, col);
	private JTable table;

	private Controller controller = new Controller();

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new App();
			}
		});
		System.out.println("Ruszy�em");
	}

	public App() {
		//do
	
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

		JMenu mnRentals = new JMenu("Wypo\u017Cyczenia");
		menuBar.add(mnRentals);

		JMenuItem mntmAddRental = new JMenuItem("Dodaj");
		mntmAddRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createRental();
			}
		});
		mnRentals.add(mntmAddRental);

		JMenuItem mntmDeleteRental = new JMenuItem("Zwr��");
		mntmDeleteRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnVideo();
			}
		});
		mnRentals.add(mntmDeleteRental);

		JMenuItem mntmEditRental = new JMenuItem("Edytuj");
		mnRentals.add(mntmEditRental);
		
		JMenuItem mntmDepricated = new JMenuItem("Przeterminowane");
		mntmDepricated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDepricatedRentals();
			}
		});
		mnRentals.add(mntmDepricated);

		JMenu mnVideos = new JMenu("Kasety");
		menuBar.add(mnVideos);

		JMenuItem mntmAddVideo = new JMenuItem("Dodaj");
		mntmAddVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addVideo();
			}
		});
		mnVideos.add(mntmAddVideo);

		JMenuItem mntmDeleteVideo = new JMenuItem("Usun");
		mntmDeleteVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteVideo();
			}
		});
		mnVideos.add(mntmDeleteVideo);

		JMenuItem mntmEditVideo = new JMenuItem("Edytuj");
		mntmEditVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editVideo();
			}
		});
		mnVideos.add(mntmEditVideo);

		JButton buttonRentals = new JButton("Wypo\u017Cyczenia");
		buttonRentals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillWithRentals(controller.getRentals());
			}
		});
		buttonRentals.setBounds(10, 235, 199, 23);
		add(buttonRentals);

		JButton buttonVideos = new JButton("Filmy");
		buttonVideos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillWithVideos(controller.getAllVideos());
			}
		});
		buttonVideos.setBounds(240, 235, 199, 23);
		add(buttonVideos);
	}

	private void fillWithRentals(ArrayList<Rental> rentals) {
		String collumnNames[] = { "Id","Imie", "Nazwisko", "Data zwrotu" };

		try {

			for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
				tableModel.removeRow(i);
			}
		} catch (NullPointerException e1) {
			System.out.println("NULL-POINTER: podczas wczytywania danych do tabeli");
		}

		tableModel.setColumnIdentifiers(collumnNames);
		try {
			for (int i = 0; i < rentals.size(); i++) {
				Rental rent = rentals.get(i);
				Object[] show = { rent.getId(),rent.getCustomer().getName(), rent.getCustomer().getSurname(),
						rent.getRentalExpireDate().getTime() };
				tableModel.insertRow(i, show);
			}
		} catch (Exception e1) {
			System.out.println(tableModel);
		}
		for(Rental r : rentals) {
			System.out.println(r.fullString());
		}
	}

	private void fillWithVideos( ArrayList<Video> videos) {
		//przyk��dowy rekord, do wywalenia
		System.out.println(videos.size());
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
			for (int i = 0; i < videos.size(); i++) {
				Video video = videos.get(i);
				Object[] show = { video.getId(), video.getName(), video.getDuration() + "minut", video.getType(),
						video.getAmount() };
				tableModel.insertRow(i, show);
			}
		} catch (Exception e1) {
			System.out.println(tableModel);
		}
		for(Video v : videos) {
			System.out.println(v.toString());
		}
	}

	@Override
	public void createRental() {
		controller.addRental();
	}

	@Override
	public void choseVideo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void returnVideo() {
		controller.removeRental();
	}

	@Override
	public void showDepricatedRentals() {
		fillWithRentals(controller.getDepricatedRentals());
	}

	@Override
	public void createVideo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addVideo() {
		controller.addVideo();
		
	}

	@Override
	public void editVideo() {
		controller.editVideo();
		
	}

	@Override
	public void deleteVideo() {
		controller.deleteVideo();
		
	}

	@Override
	public void changeRate() {
		controller.changeRate();		
	}
}
