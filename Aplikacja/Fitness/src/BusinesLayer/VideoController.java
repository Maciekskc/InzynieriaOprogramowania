package BusinesLayer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DataLayer.Components.Video;
import DataLayer.DataAccessLayer.DataOperationsImpl;
import DataLayer.DataAccessLayer.IDataOperations;
import PresentationLayer.Forms.CreateVideoDialog;

class VideoController  {
	private IDataOperations dtoData;

	public VideoController(IDataOperations dtoData) {
		super();
		this.dtoData = dtoData;
	}
	
	public boolean addVideo(){
		JDialog dialog = new CreateVideoDialog() {
				public void actionPerformed(ActionEvent e) {
					Video video = createVideo();
					if(dtoData.addVideo(video))
						JOptionPane.showMessageDialog(new Frame(), "Pomyslnie dodano kasete");
				}
	        };
	    dialog.show();
	    return true;
	}
	
	public boolean removeVideo() {
		ArrayList<Video> videos = getAllVideos();
		JDialog dialog = new JDialog() ;
		dialog.setLayout(null);
		dialog.setBounds(100, 100, 220, 110);
			JComboBox comboBoxVideos = new JComboBox<Video>();
			comboBoxVideos.setBounds(10, 10, 180, 20);
			for(int i = 0 ; i < videos.size() ; i++)
				comboBoxVideos.addItem(videos.get(i));
			
		dialog.add(comboBoxVideos);
		JButton buttonRemove = new JButton("Usun");
		buttonRemove.setBounds(50, 40, 100, 20);
		buttonRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dtoData.removeVideo((Video)comboBoxVideos.getSelectedItem()))
					JOptionPane.showMessageDialog(new Frame(), "Pomy�lnie usuni�to obiekt");
			}
		});
		dialog.add(buttonRemove);
		dialog.show();
			
		return true;
	}
	
	//edytuj video o podanym numerze id
	public boolean editVideoById() {
		//otwieramy panel do tworzenia video
		JDialog dialog = new CreateVideoDialog() {
			public void actionPerformed(ActionEvent e) {
				try{
					Video video = createVideo();
					if(dtoData.editVideoById(video, video.getId()))			
						JOptionPane.showMessageDialog(new Frame(), "Kaseta edytowana, Nowe Dane:" + video.toString());
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(new Frame(), "Nie znaleziono takiego numeru id lub dane s� neipoprawne");
				}
				setVisible(false);
			}
        };
    dialog.show();
		return true;
	}
	
	//zwracal liste tytu��w z dopasowanymi kryteriami
	public ArrayList<Video> getVideos(int id, String name, String type){
		ArrayList<Video> videos = getAllVideos();
		
		for(Video video: videos) {
			if(video.getId() == id	||	video.getName() == name	||	video.getType() == type)
				videos.add(video);
		}
		
		return videos;
	}

	//pobieranie listy video
	public  ArrayList<Video>  getAllVideos() {
		return dtoData.getAllVideos();
	}

}