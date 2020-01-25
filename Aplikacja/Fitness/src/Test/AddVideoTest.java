package Test;

import DataLayer.Components.Video;
import fit.ColumnFixture;
import fit.Fixture;

public class AddVideoTest extends ColumnFixture {

	public String video;
	
	public boolean addVideo() {
		try {
			int listVideoSize = SetUp.dtoData.getAllVideos().size();
			Video _video = new Video(video);
			SetUp.dtoData.addVideo(_video);
			int listVideoSizeAfterAddOperation = SetUp.dtoData.getAllVideos().size();
			if(listVideoSizeAfterAddOperation > listVideoSize)
				return true;
		}catch (Exception e) {
			
		}
		
		return false;
	}
		
}
