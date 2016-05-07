package th.co.itgenius.mvcandroidapp.manager.http;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.POST;
import th.co.itgenius.mvcandroidapp.dao.PhotoItemCollectionDao;

/**
 * Created by samit on 6/5/2559.
 */
public interface ApiService {

    @POST("list")
    Call<PhotoItemCollectionDao> loadPhotoList();

}
