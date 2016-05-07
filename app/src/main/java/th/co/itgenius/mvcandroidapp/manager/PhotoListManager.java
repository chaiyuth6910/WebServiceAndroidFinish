package th.co.itgenius.mvcandroidapp.manager;

import android.content.Context;

import com.itgenius.mvclibrary.manager.Contextor;

import th.co.itgenius.mvcandroidapp.dao.PhotoItemCollectionDao;


public class PhotoListManager {

    private static PhotoListManager instance;

    public static PhotoListManager getInstance() {
        if (instance == null)
            instance = new PhotoListManager();
        return instance;
    }

    private Context mContext;
    private PhotoItemCollectionDao dao;

    private PhotoListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    /***** Getter & Setter ****/
    public PhotoItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
    }
}
