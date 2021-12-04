package com.example.practicekotlin.ztest.lesson9

import android.Manifest
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.practicekotlin.databinding.FragmentContentProviderBinding

class ContentProviderFragment : Fragment() {


    private lateinit var binding: FragmentContentProviderBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentProviderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()

    }

    private fun checkPermission() {
        context?.let {
            if (ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                getContact()
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                AlertDialog.Builder(it)
                    .setTitle("Нужен доступ")
                    .setMessage("Дайте доступ")
                    .setPositiveButton("На") { dialog, which ->
                        myRequestPermission()
                    }
                    .setNegativeButton("НЕ") { dialog, which ->
                        dialog.dismiss()
                    }
                    .create().show()
            } else {
                myRequestPermission()
            }
        }
    }

    private val REQUEST_CODE = 777

    private fun myRequestPermission() {
        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContact()
                } else {
                    context?.let {
                        AlertDialog.Builder(it)
                            .setTitle("Нужен доступ1")
                            .setMessage("Дайте доступ1")
                            .setPositiveButton("На1") { dialog, which ->
                                myRequestPermission()
                            }
                            .setNegativeButton("НЕ1") { dialog, which ->
                                dialog.dismiss()
                            }
                            .create().show()
                    }
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    private fun getContact() {
        context?.let {
            val contentResolver: ContentResolver = it.contentResolver
            val cursor: Cursor? = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null, null, null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )
            cursor?.let { cursor ->
                for (i in 0 until cursor.count) {
                    if (cursor.moveToPosition(i)) {
                        val name =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        binding.containerForContacts.addView(TextView(it).apply {
                            text = name
                            textSize = 30f
                        })
                    }
                }

            }
        }
    }



companion object {
    fun newInstance() = ContentProviderFragment()
}

}