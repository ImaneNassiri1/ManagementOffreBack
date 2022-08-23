package com.example.back_stage_application.Service;

import com.example.back_stage_application.Document.File;
import com.example.back_stage_application.Document.Question;
import com.example.back_stage_application.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FileService {
    FileRepository fileRepository;
    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    /////////////////////////////////////////1/METHOD TO ADD QUESTION///////////////////////////////////////////////////
    public File addQuestion(File file){
        return fileRepository.save(file);
    }
    public Iterable<File> ADDLISTOFQUESTION(List<File> file) {
        return fileRepository.saveAll(file);
    }
    ///////////////////////////////////////2/METHOD TO READ FILE////////////////////////////////////////////////////
    public List<File> getAllFiles(){
        return fileRepository.findAll();
    }
    ////////////////////////////////////// METHOD TO HAVE FILE BY CATEGORY /////////////////////////////////////////
    public List<File> getByCategory(File file){
        String category=file.getCategory();
        return fileRepository.findByCategory(category);
    }
    /////////////////////////////////////METHOD TO HAVE FILE BY DATE ///////////////////////////////////////////////
    public List<File> getByDate(File file){
        Date date=file.getDate();
        System.out.println(date);
        return fileRepository.findByDate(date);
    }
    /////////////////////////////////3/METHOD TO UPDATE FILE////////////////////////////////////////////////////////
    public File updateFile(File file){
        return fileRepository.save(file);
    }
    /////////////////////////////////4/METHOD TO DELETE FILE////////////////////////////////////////////////////////

    public void delete(String id)
    {
        fileRepository.deleteById(id);
    }


}
