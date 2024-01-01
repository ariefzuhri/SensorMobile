package sv.ugm.sensormobile.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import sv.ugm.sensormobile.BuildConfig
import sv.ugm.sensormobile.data.source.remote.api.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    
    @Singleton
    @Provides
    fun provideChuckerInterceptor(
        application: Application,
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(application)
            .build()
    }
    
    @Singleton
    @Provides
    fun provideCertificatePinner(): CertificatePinner {
        val hostname = BuildConfig.apiHostname
        return CertificatePinner.Builder()
            .add(hostname, "sha256/${BuildConfig.apiPublicKey1}")
            .add(hostname, "sha256/${BuildConfig.apiPublicKey2}")
            .add(hostname, "sha256/${BuildConfig.apiPublicKey3}")
            .build()
    }
    
    @Singleton
    @Provides
    fun provideOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        certificatePinner: CertificatePinner,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .certificatePinner(certificatePinner)
            .build()
    }
    
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.apiBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
    }
    
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    
}