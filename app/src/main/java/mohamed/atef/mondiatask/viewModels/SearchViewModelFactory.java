package mohamed.atef.mondiatask.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

public class SearchViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final Application app;

    public SearchViewModelFactory(Application application){
        this.app=application;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T) new SearchViewModel(app);
    }
}
