package utilites;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

public class registerCompanyData {

	private byte[] filedata;
	
	public registerCompanyData() {}
	
	public byte[] getFileData() {
        return filedata;
    }

    @FormParam("image")
    @Consumes("application/octet-stream")
    public void setFileData(final byte[] filedata) {
        this.filedata = filedata;
    }
}
