package com.sachin.personaldiary.fragments


import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sachin.personaldiary.R
import com.sachin.personaldiary.models.Diary
import com.sachin.personaldiary.utils.clearEditText
import com.sachin.personaldiary.utils.setupDialog
import com.sachin.personaldiary.utils.validateEditText
import com.sachin.personaldiary.viewmodels.DiaryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID


class HomeFragment : Fragment() {
    private lateinit var addDiaryFABtn: ExtendedFloatingActionButton

    private val loadingDialog: Dialog by lazy {
        Dialog(requireContext(), R.style.DialogCustomTheme).apply {
            setupDialog(R.layout.loading_dialog)
        }
    }
    private val addDiaryDialog: Dialog by lazy {
        Dialog(requireContext(), R.style.DialogCustomTheme).apply {
            setupDialog(R.layout.add_diary_dialog)
        }
    }
    private val diaryViewModel: DiaryViewModel by lazy {
        ViewModelProvider(this)[DiaryViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add diary start
        val addCloseImg = addDiaryDialog.findViewById<ImageView>(R.id.closeImg)
        addCloseImg.setOnClickListener { addDiaryDialog.dismiss() }

        val addETTitle = addDiaryDialog.findViewById<TextInputEditText>(R.id.edDiaryTitle)
        val addETTitleL = addDiaryDialog.findViewById<TextInputLayout>(R.id.edDiaryTitleL)

        addETTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(addETTitle, addETTitleL)
            }

        })

        val addETDesc = addDiaryDialog.findViewById<TextInputEditText>(R.id.edDiaryDesc)
        val addETDescL = addDiaryDialog.findViewById<TextInputLayout>(R.id.edDiaryDescL)

        addETDesc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(addETDesc, addETDescL)
            }
        })
        addDiaryFABtn = view.findViewById(R.id.addDiaryFABtn)
        addDiaryFABtn.setOnClickListener {
            clearEditText(addETTitle, addETTitleL)
            clearEditText(addETDesc, addETDescL)
            addDiaryDialog.show()
        }
        //
        val multiplePhotoPickLauncher =
            registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()){ uris ->
                for (image in uris){
                    // single image upload function
                    newDiaryUpload( image)
                }
            }
        val saveDiaryBtn = addDiaryDialog.findViewById<Button>(R.id.saveDiaryBtn)
        saveDiaryBtn.setOnClickListener {
            multiplePhotoPickLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
        //
        // Add diary end
    }
    private fun newDiaryUpload(imageUri: Uri) {
        val addETTitle = addDiaryDialog.findViewById<TextInputEditText>(R.id.edDiaryTitle)
        val addETTitleL = addDiaryDialog.findViewById<TextInputLayout>(R.id.edDiaryTitleL)

        addETTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(addETTitle, addETTitleL)
            }

        })

        val addETDesc = addDiaryDialog.findViewById<TextInputEditText>(R.id.edDiaryDesc)
        val addETDescL = addDiaryDialog.findViewById<TextInputLayout>(R.id.edDiaryDescL)

        addETDesc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(addETDesc, addETDescL)
            }
        })
        addDiaryFABtn.setOnClickListener {
            clearEditText(addETTitle, addETTitleL)
            clearEditText(addETDesc, addETDescL)
            addDiaryDialog.show()
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {if (validateEditText(addETTitle, addETTitleL)
                && validateEditText(addETDesc, addETDescL)
            ){  val contentResolver = requireContext().contentResolver
                val newDiary = contentResolver.openInputStream(imageUri)?.readBytes()?.let {
                    Diary(
                        UUID.randomUUID().toString(),
                        addETTitle.text.toString().trim(),
                        addETDesc.text.toString().trim(),
                        Date(),
                        it
                    )
                }
                addDiaryDialog.dismiss()
                //diaryViewModel.insertDiary(newDiary)
                newDiary?.let {
                    diaryViewModel.insertDiary(it)
                }
            }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
