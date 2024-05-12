package com.sachin.personaldiary.fragments

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sachin.personaldiary.R
import com.sachin.personaldiary.adapters.DiaryRVVBListAdapter
import com.sachin.personaldiary.models.Diary
import com.sachin.personaldiary.utils.hideKeyBoard
import com.sachin.personaldiary.utils.setupDialog
import com.sachin.personaldiary.utils.validateEditText
import java.util.Date
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sachin.personaldiary.utils.Status
import com.sachin.personaldiary.utils.longToastShow
import com.sachin.personaldiary.viewmodels.DiaryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.sachin.personaldiary.utils.*
import java.util.UUID


class ViewDiaryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var scrollView: NestedScrollView
    private lateinit var SearchTextInput: TextInputEditText
    private lateinit var sortImg: ImageView
    private lateinit var addDiaryFABtn: ExtendedFloatingActionButton

    private val addDiaryDialog: Dialog by lazy {
        Dialog(requireContext(), R.style.DialogCustomTheme).apply {
            setupDialog(R.layout.add_diary_dialog)
        }
    }
    private val diaryViewModel: DiaryViewModel by lazy {
        ViewModelProvider(this)[DiaryViewModel::class.java]
    }
    private val loadingDialog: Dialog by lazy {
        Dialog(requireContext(), R.style.DialogCustomTheme).apply {
            setupDialog(R.layout.loading_dialog)
        }
    }
    private val updateDiaryDialog: Dialog by lazy {
        Dialog(requireContext(), R.style.DialogCustomTheme).apply {
            setupDialog(R.layout.update_diary_dialog)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_diary, container, false)
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
        // Update Diary Start
        val updateETTitle = updateDiaryDialog.findViewById<TextInputEditText>(R.id.edDiaryTitle)
        val updateETTitleL = updateDiaryDialog.findViewById<TextInputLayout>(R.id.edDiaryTitleL)

        updateETTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(updateETTitle, updateETTitleL)
            }

        })

        val updateETDesc = updateDiaryDialog.findViewById<TextInputEditText>(R.id.edDiaryDesc)
        val updateETDescL = updateDiaryDialog.findViewById<TextInputLayout>(R.id.edDiaryDescL)

        updateETDesc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEditText(updateETDesc, updateETDescL)
            }
        })

        val updateCloseImg = updateDiaryDialog.findViewById<ImageView>(R.id.closeImg)
        updateCloseImg.setOnClickListener { updateDiaryDialog.dismiss() }
        val updateDiaryBtn = updateDiaryDialog.findViewById<Button>(R.id.updateDiaryBtn)

        // Update Diary End

        val diaryRVVBListAdapter = DiaryRVVBListAdapter { type, position, diary ->
            when (type) {
                "delete" -> {
                    diaryViewModel.deleteDiaryUsingId(diary.id)
                    restoreDeletedDiary(diary)
                }
                "update" -> {
                    updateETTitle.setText(diary.title)
                    updateETDesc.setText(diary.description)
                    updateDiaryBtn.setOnClickListener {
                        if (validateEditText(updateETTitle, updateETTitleL)
                            && validateEditText(updateETDesc, updateETDescL)
                        ) {
                            val updateDiary = Diary(
                                diary.id,
                                updateETTitle.text.toString().trim(),
                                updateETDesc.text.toString().trim(),
                                Date(),
                                diary.imageData
                            )
                            //hideKeyBoard(it)
                            updateDiaryDialog.dismiss()
                            diaryViewModel.updateDiary(updateDiary)
                        }
                    }
                    updateDiaryDialog.show()
                }
            }
        }
        recyclerView = view.findViewById(R.id.diaryRV)
        scrollView = view.findViewById(R.id.nestedScrollView)
        recyclerView.adapter = diaryRVVBListAdapter
        ViewCompat.setNestedScrollingEnabled(recyclerView,false)
        diaryRVVBListAdapter.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
//              mainBinding.diaryRV.smoothScrollToPosition(positionStart)
                scrollView.smoothScrollTo(0,positionStart)
            }
        })
        callGetDiaryList(diaryRVVBListAdapter)
        callSortByLiveData()
        statusCallback()
        sortImg = view.findViewById<ImageView>(R.id.sortImg)
        SearchTextInput = view.findViewById(R.id.edSearch) // Initialize SearchTextInput here
        callSearch()
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

    private fun callGetDiaryList(diaryRecyclerViewAdapter: DiaryRVVBListAdapter) {
        CoroutineScope(Dispatchers.Main).launch {
            diaryViewModel
                .diaryStateFlow
                .collectLatest {
                    Log.d("status", it.status.toString())

                    when (it.status) {
                        Status.LOADING -> {
                            loadingDialog.show()
                        }
                        Status.SUCCESS -> {
                            loadingDialog.dismiss()
                            it.data?.collect { diaryList ->
                                diaryRecyclerViewAdapter.submitList(diaryList)
                            }
                        }
                        Status.ERROR -> {
                            loadingDialog.dismiss()
                            //it.message?.let { it1 -> longToastShow(it1) }
                        }
                    }
                }
        }
    }
    private fun callSortByLiveData(){
        diaryViewModel.sortByLiveData.observe(viewLifecycleOwner) { sortBy ->
            diaryViewModel.getDiaryList(sortBy.second, sortBy.first)
        }
    }
    private fun statusCallback() {
        diaryViewModel
            .statusLiveData
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        when (it.data as StatusResult) {
                            StatusResult.Added -> {
                                Log.d("StatusResult", "Added")
                            }

                            StatusResult.Deleted -> {
                                Log.d("StatusResult", "Deleted")

                            }

                            StatusResult.Updated -> {
                                Log.d("StatusResult", "Updated")

                            }
                        }
                        //it.message?.let { it1 -> longToastShow(it1) }
                    }

                    Status.ERROR -> {
                        loadingDialog.dismiss()
                        //it.message?.let { it1 -> longToastShow(it1) }
                    }
                }
            }
    }
    private fun restoreDeletedDiary(deletedDiary: Diary) {
        view?.let { rootView ->
            val snackBar = Snackbar.make(
                rootView, "Deleted '${deletedDiary.title}'",
                Snackbar.LENGTH_LONG
            )
            snackBar.setAction("Undo") {
                diaryViewModel.insertDiary(deletedDiary)
            }
            snackBar.show()
        }
    }
    private fun callSearch() {
        SearchTextInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(query: Editable) {
                if (query.toString().isNotEmpty()) {
                    diaryViewModel.searchDiaryList(query.toString())
                } else {
                    callSortByLiveData()
                }
            }
        })
        SearchTextInput.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //hideKeyBoard(v)
                return@setOnEditorActionListener true
            }
            false
        }
        callSortByDialog()
    }
    private fun callSortByDialog() {
        var checkedItem = 0   // 2 is default item set
        val items = arrayOf("Title Ascending", "Title Descending","Date Ascending","Date Descending")

        sortImg.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Sort By")
                .setPositiveButton("Ok") { _, _ ->
                    when (checkedItem) {
                        0 -> {
                            diaryViewModel.setSortBy(Pair("title",true))
                        }
                        1 -> {
                            diaryViewModel.setSortBy(Pair("title",false))
                        }
                        2 -> {
                            diaryViewModel.setSortBy(Pair("date",true))
                        }
                        else -> {
                            diaryViewModel.setSortBy(Pair("date",false))
                        }
                    }
                }
                .setSingleChoiceItems(items, checkedItem) { _, selectedItemIndex ->
                    checkedItem = selectedItemIndex
                }
                .setCancelable(false)
                .show()
        }
    }
}
