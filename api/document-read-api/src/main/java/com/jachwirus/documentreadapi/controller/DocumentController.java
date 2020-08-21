package com.jachwirus.documentreadapi.controller;

import com.jachwirus.documentreadapi.dto.model.DocumentDto;
import com.jachwirus.documentreadapi.service.DocumentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/documents")
public class DocumentController {
    public static final String ENTIRE_LIST = "ENTIRE";

    private final DocumentService documentService;
    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }

    @GetMapping("")
    @ApiOperation(value = "위키 데이터 리스트 조회")
    public CollectionModel<EntityModel<DocumentDto>> findList(
            @RequestParam(defaultValue = ENTIRE_LIST, value = "category", name="category", required = false)
            String category
    ){
        CollectionModel<EntityModel<DocumentDto>> collection = documentService.findDocumentsList(category);
        return collection;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "위키 문서 상세 조회")
    public EntityModel<DocumentDto> findOne(@PathVariable Long id){
        //need to be changed
        EntityModel<DocumentDto> model = documentService.findDocumentById(id);
        return model;
    }
}