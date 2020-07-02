package com.samsung.hyunjaee.scopedstorage.saf

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.samsung.hyunjaee.scopedstorage.R
import com.samsung.hyunjaee.scopedstorage.databinding.FragmentSystemPickerBinding
import timber.log.Timber

class SystemPickerFragment : Fragment() {

    private lateinit var viewModel: SystemPickerViewModel
    private lateinit var binding: FragmentSystemPickerBinding

    companion object {

        const val CREATE_FILE = 1
        const val PICK_FILE = 2
        const val PICK_DIRECTORY = 3

        @JvmStatic
        fun newInstance(): SystemPickerFragment {
            return SystemPickerFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate()")
        viewModel = ViewModelProvider(this).get(SystemPickerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView()")
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_system_picker,
            container,
            false
        )
        binding.newFileButton.setOnClickListener { createNewFile() }
        binding.openDocumentButton.setOnClickListener { openFile() }
        binding.openDocumentTreeButton.setOnClickListener { openDirectory() }

        return binding.root
    }

    // todo :: https://codechacha.com/ko/android-storage-access-framework/
    private fun createNewFile() {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"
            putExtra(Intent.EXTRA_TITLE, "invoice.pdf")
        }
        startActivityForResult(intent, CREATE_FILE)
    }

    private fun openFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
        }
        startActivityForResult(intent, PICK_FILE)
    }

    private fun openDirectory() {
        // Choose a directory using the system's file picker.
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE).apply {
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        startActivityForResult(intent, PICK_DIRECTORY)
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int, resultData: Intent?
    ) {
        if (requestCode == PICK_DIRECTORY && resultCode == Activity.RESULT_OK) {
            // The result data contains a URI for the document or directory that
            // the user selected.
            resultData?.data?.also { uri ->
                Timber.i("uri $uri")
            }
        }
    }
}