import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { shareReplay } from 'rxjs';
import { STUDENT_BASE_URL } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http:HttpClient) { }

  updateProfile(studentId, fields){
    return this.http.put(`${STUDENT_BASE_URL}`+"/profile/"+`${studentId}`, fields).pipe(shareReplay());
  }
}
