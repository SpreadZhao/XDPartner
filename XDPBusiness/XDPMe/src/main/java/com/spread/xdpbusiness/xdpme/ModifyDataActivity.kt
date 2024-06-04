package com.spread.xdpbusiness.xdpme

import android.Manifest
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.spread.xdpbusiness.xdpme.databinding.ActivityModifyDataBinding
import com.spread.xdplib.adapter.base.BaseViewBindingActivity
import com.spread.xdplib.adapter.entry.BlogBean
import com.spread.xdplib.adapter.entry.ChangeUserBean
import com.spread.xdplib.adapter.utils.ProgressDialogUtils
import com.spread.xdplib.adapter.utils.TestLogger
import com.spread.xdpnetwork.network.service.LoginServiceSingle
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class ModifyDataActivity : BaseViewBindingActivity<ActivityModifyDataBinding>(),
    View.OnClickListener {
    private lateinit var popupWindow: PopupWindow
    private var popupView: View? = null

    //相机拍照保存的位置
    private lateinit var photoUri: Uri
    private var mFile: File? = null
    override fun getViewBinding(): ActivityModifyDataBinding {
        return ActivityModifyDataBinding.inflate(layoutInflater)
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 1000 //权限
        private const val REQUEST_CODE_ALBUM = 1001 //相册
        private const val REQUEST_CODE_CAMERA = 1002 //相机
    }

    override fun initView() {
        binding.titleBar.setLeftListener { finish() }
        binding.imageAdd.setOnClickListener(this)
        binding.buttonPublish.setOnClickListener(this)
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
        ).apply {
            showAtLocation(binding.layoutParent, Gravity.BOTTOM, 0, 0)
            setBackgroundDrawable(resources.getDrawable(com.spread.xdplib.R.color.white))
            setOnDismissListener { setAlpha(1.0f) }
        }
        setAlpha(0.3f)
        //把背景还原
        initPopupView()
    }

    private fun setAlpha(f: Float) {
        val lp = window.attributes
        lp.alpha = f
        window.attributes = lp
    }

    private fun initPopupView() {
        popupView!!.findViewById<Button>(R.id.button_camera).setOnClickListener(this)
        popupView!!.findViewById<Button>(R.id.button_photo).setOnClickListener(this)
        popupView!!.findViewById<Button>(R.id.button_cancel).setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.image_add -> createPopupWindow()
            R.id.button_cancel -> popupWindow.dismiss()
            R.id.button_photo -> openPhoto()
            R.id.button_camera -> checkPermission()
            R.id.button_publish -> publishBlog()
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

    private fun getFileByUrl(uri: Uri) {
        val contentResolver: ContentResolver = contentResolver
        val photoFile = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "photo_${System.currentTimeMillis()}.jpg"
        )
        // 创建一个文件输出流来写入文件
        val outputStream = FileOutputStream(photoFile)
        // 将输入流的内容复制到输出流，即保存到文件中
        val inputStreamPhoto = contentResolver.openInputStream(uri)
        inputStreamPhoto?.copyTo(outputStream)
        // 关闭流
        inputStreamPhoto?.close()
        outputStream.close()
        mFile = photoFile
    }

    private fun checkPermission() {
        popupWindow.dismiss()
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            openCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoUri = getDestinationUri()
        TestLogger.logd("openCamera $photoUri")
        photoUri =
                //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
            FileProvider.getUriForFile(this, "$packageName.fileProvider", File(photoUri.path!!))
        //android11以后强制分区存储，外部资源无法访问，所以添加一个输出保存位置，然后取值操作
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }

    private fun getDestinationUri(): Uri {
        val fileName = String.format("fr_crop_%s.jpg", System.currentTimeMillis())
        val cropFile = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
        return Uri.fromFile(cropFile)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        TestLogger.logd("onActivityResult->requestCode:$requestCode + resultCode: $resultCode")
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_ALBUM -> {
                    TestLogger.logd("REQUEST_CODE_ALBUM: " + data?.data.toString())
                    try {
                        val contentResolver: ContentResolver = contentResolver
                        val inputStream = data?.data?.let { contentResolver.openInputStream(it) }
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        TestLogger.logd("REQUEST_CODE_ALBUM->bitmap:$bitmap ")
                        binding.imageAdd.setImageBitmap(bitmap)
                        inputStream?.close()
                        // 在这里你已经有了Bitmap对象，可以进行后续操作
                        data?.data?.let { getFileByUrl(it) }

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
                    TestLogger.logd("REQUEST_CODE_CAMERA: $photoUri")
                    try {
                        val contentResolver: ContentResolver = contentResolver
                        val inputStream = contentResolver.openInputStream(photoUri)
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        TestLogger.logd("REQUEST_CODE_CAMERA->bitmap:$bitmap ")
                        binding.imageAdd.setImageBitmap(bitmap)
                        getFileByUrl(photoUri)
                        // 在这里你已经有了Bitmap对象，可以进行后续操作
                    } catch (e: FileNotFoundException) {
                        // 处理文件未找到的情况
                        e.printStackTrace()
                    } catch (e: IOException) {
                        // 处理输入输出异常
                        e.printStackTrace()
                    }
                }

            }
        }
    }

    private fun publishBlog(){
        ProgressDialogUtils.showProgressDialog(this,"修改中")
        TestLogger.logd("publishBlog $mFile")
        mFile?.let { file ->
            LoginServiceSingle.instance.policy(file) {
                val major = binding.majorEdit.text.toString()
                val content = binding.desEdit.text.toString()
                val name = binding.nameEdit.text.toString()
                val blogBean = ChangeUserBean(
                    icon = it,
                    majorName = getDefault(major),
                    myDescription = getDefault(content),
                    nickName = getDefault(name),
                    qq = "111"
                )
                LoginServiceSingle.instance.changeUser(blogBean) { msg ->
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                    ProgressDialogUtils.hideProgressDialog()
                }
            }
        }
    }

    private fun getDefault(s :String?) :String {
        return if (s.isNullOrEmpty()){
            "未设置"
        } else {
            s
        }
    }
}