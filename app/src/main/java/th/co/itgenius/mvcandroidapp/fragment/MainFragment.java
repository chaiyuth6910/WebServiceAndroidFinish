package th.co.itgenius.mvcandroidapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import th.co.itgenius.mvcandroidapp.R;
import th.co.itgenius.mvcandroidapp.adapter.PhotoListAdapter;
import th.co.itgenius.mvcandroidapp.dao.PhotoItemCollectionDao;
import th.co.itgenius.mvcandroidapp.manager.HttpManager;
import th.co.itgenius.mvcandroidapp.manager.PhotoListManager;

/**
 * Created by samit on 6/5/2559.
 */
public class MainFragment extends Fragment {
    ListView listView;
    PhotoListAdapter listAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {
        listView = (ListView) rootView.findViewById(R.id.listView);
        listAdapter = new PhotoListAdapter();
        listView.setAdapter(listAdapter);

        // ทดสอบดึงข้อมูลจาก Web Service ด้วย Retrofit
        Call<PhotoItemCollectionDao> call = HttpManager.getInstance().getService().loadPhotoList();
        call.enqueue(new Callback<PhotoItemCollectionDao>() {
            @Override
            public void onResponse(Call<PhotoItemCollectionDao> call,
                                   Response<PhotoItemCollectionDao> response) {

                    // เช็คว่า response success หรือไม่
                    if(response.isSuccessful()){
                        PhotoItemCollectionDao dao = response.body();

                        // ส่งข้อมูลเข้า model
                        PhotoListManager.getInstance().setDao(dao);
                        listAdapter.notifyDataSetChanged();
                        // แสดงผล
                        /*Toast.makeText(getActivity(),
                                dao.getData().get(0).getCaption(),
                                Toast.LENGTH_LONG).show();*/
                    }else{
                        try{
                            Toast.makeText(getActivity(),
                                    response.errorBody().string(),
                                    Toast.LENGTH_LONG).show();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }

            }

            @Override
            public void onFailure(Call<PhotoItemCollectionDao> call,
                                  Throwable t) {
                        Toast.makeText(getActivity(),
                         t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }


}
