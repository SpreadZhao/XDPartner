package com.spread.xdpartner.add

import android.Manifest
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.xdpartner.R
import com.example.xdpartner.databinding.ActivityAddBinding
import com.spread.xdpartner.base.BaseViewBindingActivity
import com.spread.xdpartner.test.TestLogger.log
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException


class AddActivity : BaseViewBindingActivity<ActivityAddBinding>(), View.OnClickListener {
    lateinit var popupWindow: PopupWindow
    var popupView: View? = null
    //相机拍照保存的位置
    private lateinit var photoUri: Uri


    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 1000 //权限
        private const val REQUEST_CODE_ALBUM = 1001 //相册
        private const val REQUEST_CODE_CAMERA = 1002 //相机
    }

    private fun createPopupWindow() {
        if (popupView == null) {
            popupView = layoutInflater.inflate(R.layout.popup_unload_image, null)
        }
        popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
        popupWindow.showAtLocation(
            binding.layoutParent,
            Gravity.BOTTOM,
            0,
            0
        ) //底部显示弹窗
        popupWindow.setBackgroundDrawable(resources.getDrawable(android.R.color.white))
        setAlpha(0.3f)
        //把背景还原
        popupWindow.setOnDismissListener { setAlpha(1.0f) }
        initPopupView()
    }

    private fun initPopupView() {
        val camera_btn = popupView!!.findViewById<Button>(R.id.button_camera)
        val pic_btn = popupView!!.findViewById<Button>(R.id.button_photo)
        val cancel_btn = popupView!!.findViewById<Button>(R.id.button_cancel)
        camera_btn.setOnClickListener(this)
        pic_btn.setOnClickListener(this)
        cancel_btn.setOnClickListener(this)
    }

    private fun setAlpha(f: Float) {
        val lp = window.attributes
        lp.alpha = f
        window.attributes = lp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.imageAdd.setOnClickListener(this)
    }

    override fun getViewBinding(): ActivityAddBinding {
        return ActivityAddBinding.inflate(layoutInflater)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.image_add -> createPopupWindow()
            R.id.button_cancel -> popupWindow.dismiss()
            R.id.button_photo -> openPhoto()
            R.id.button_camera -> checkPermission()
        }
    }
    private fun getDestinationUri(): Uri {
        val fileName = String.format("fr_crop_%s.jpg", System.currentTimeMillis())
        val cropFile = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
        return Uri.fromFile(cropFile)
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoUri = getDestinationUri()
        log("openCamera $photoUri")
        photoUri =
                //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
            FileProvider.getUriForFile(this, "$packageName.fileProvider", File(photoUri.path!!))
        //android11以后强制分区存储，外部资源无法访问，所以添加一个输出保存位置，然后取值操作
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }
    private fun checkPermission() {
        popupWindow.dismiss()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_PERMISSIONS)
        }
    }


    private fun openPhoto() {
        popupWindow.dismiss()
        val intent = Intent()
        intent.type = "image/*"
        intent.action = "android.intent.action.GET_CONTENT"
        intent.addCategory("android.intent.category.OPENABLE")
        startActivityForResult(intent, REQUEST_CODE_ALBUM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        log("onActivityResult->requestCode:$requestCode + resultCode: $resultCode")
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_ALBUM -> {
                    log("REQUEST_CODE_ALBUM: " + data?.data.toString())
                    try {
                        val contentResolver: ContentResolver = contentResolver
                        val inputStream = data?.data?.let { contentResolver.openInputStream(it) }
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        log("REQUEST_CODE_ALBUM->bitmap:$bitmap ")
                        binding.imageAdd.setImageBitmap(bitmap)
                        inputStream?.close()
                        // 在这里你已经有了Bitmap对象，可以进行后续操作
                    } catch (e: FileNotFoundException) {
                        // 处理文件未找到的情况
                        e.printStackTrace()
                    } catch (e: IOException) {
                        // 处理输入输出异常
                        e.printStackTrace()
                    }
                }

                REQUEST_CODE_CAMERA -> {
                    //从保存的位置取值
                    log("REQUEST_CODE_CAMERA: $photoUri")
                    try {
                        val contentResolver: ContentResolver = contentResolver
                        val inputStream = contentResolver.openInputStream(photoUri)
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        log("REQUEST_CODE_ALBUM->bitmap:$bitmap ")
                        binding.imageAdd.setImageBitmap(bitmap)
                        inputStream?.close()
                        // 在这里你已经有了Bitmap对象，可以进行后续操作
                    } catch (e: FileNotFoundException) {
                        // 处理文件未找到的情况
                        e.printStackTrace()
                    } catch (e: IOException) {
                        // 处理输入输出异常
                        e.printStackTrace()
                    }
                    var bitmap = BitmapFactory.decodeFile(photoUri.toString())
                }

            }
        }
    }
}
