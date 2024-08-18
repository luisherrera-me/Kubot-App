package com.kuby.kubot.util

import com.kuby.kubot.domain.model.ErrorResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

object ResponseToRequest {

    fun <T> send(response: Response<T>): Resource<T>{
        return try {
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            }
            else {
                val error: ErrorResponse? = ConvertErrorBody.convert(response.errorBody())
                Resource.Error(Exception(
                    (error?.message + error?.statusCode) ?: "Error desconocido"
                ))
            }
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(Exception("Error al iniciar sesión"))
        }
        catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(Exception("Verifica tu conexión"))
        }
        catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}