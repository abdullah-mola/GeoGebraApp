package abdullah.mola.geogebra.data.remote

import abdullah.mola.geogebra.data.Repository
import abdullah.mola.geogebra.data.local.GeoGebraDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {


    @Singleton
    @Provides
    fun geoGebraApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.geogebra.org/v1.0/search/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun provideGeoGebraApi( retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesGeoGebraRepository(api: ApiService, locald: GeoGebraDao): Repository {
        return Repository(api,locald)
    }
}