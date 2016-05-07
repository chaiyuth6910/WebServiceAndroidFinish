package th.co.itgenius.mvcandroidapp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import th.co.itgenius.mvcandroidapp.dao.PhotoItemDao;
import th.co.itgenius.mvcandroidapp.manager.PhotoListManager;
import th.co.itgenius.mvcandroidapp.view.PhotoListItem;

/**
 * Created by samit on 7/5/2559.
 */
public class PhotoListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        if(PhotoListManager.getInstance().getDao() == null){
            return 0;
        }else{
            return PhotoListManager.getInstance().getDao().getData().size();
        }
    }

    @Override
    public Object getItem(int position) {
        return PhotoListManager.getInstance().getDao().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PhotoListItem item;
        if(convertView != null){
            item = (PhotoListItem) convertView;
        }else{
            item = new PhotoListItem(parent.getContext());

            // Set dao ไปยัง Custom View
            PhotoItemDao dao = (PhotoItemDao) getItem(position);
            item.setNameText(dao.getCaption());
            item.setDescriptionText(dao.getUsername()+"\n"+dao.getCamera());
            item.setImageUrl(dao.getImageUrl());
        }
        return  item;
    }
}
