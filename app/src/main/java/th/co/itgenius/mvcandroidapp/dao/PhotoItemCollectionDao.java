package th.co.itgenius.mvcandroidapp.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by samit on 6/5/2559.
 */
public class PhotoItemCollectionDao {
    @SerializedName("success") private boolean success;
    @SerializedName("data")         private List<PhotoItemDao> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<PhotoItemDao> getData() {
        return data;
    }

    public void setData(List<PhotoItemDao> data) {
        this.data = data;
    }
}
