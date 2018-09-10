import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from "rxjs";

import { CamundaRestService } from '../camunda-rest.service'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private fileToUpload: File = null;
  private SUCCESS: boolean = false;
  
  constructor(private camundaRestService: CamundaRestService) { }

  ngOnInit() {
  }

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
    this.uploadFileToActivity();
  }

  uploadFileToActivity() {
    this.camundaRestService.deployProcess(this.fileToUpload).subscribe(data => {
      this.SUCCESS = true;
      }, error => {
        console.log(error);
    });
  }
}
