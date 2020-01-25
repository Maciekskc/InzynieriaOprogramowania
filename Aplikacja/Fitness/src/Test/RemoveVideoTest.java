package Test;

import DataLayer.Components.Video;
import fit.ColumnFixture;

public class RemoveVideoTest extends ColumnFixture {

	public String video;
	
	public boolean removeVideo() {
		try {
			
			int listVideoSize = SetUp.dtoData.getAllVideos().size();
			Video _video = new Video(video);
			SetUp.dtoData.removeVideo(_video);
			int listVideoSizeAfterAddOperation = SetUp.dtoData.getAllVideos().size();
			if(listVideoSizeAfterAddOperation != listVideoSize)
					return true;
		}catch (Exception e) {
			
		}
		
		return false;
	}
}
