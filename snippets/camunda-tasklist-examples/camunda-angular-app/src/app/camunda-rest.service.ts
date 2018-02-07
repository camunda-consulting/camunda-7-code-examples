import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ProcessDefinition } from './schemas/ProcessDefinition';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class CamundaRestService {
  private engineRestUrl = 'http://localhost:8080/engine-rest/'

  constructor(private http: HttpClient) {

  }

  getProcessDefinitionTaskKey(processDefinitionKey): Observable<any> {
    const url = `${this.engineRestUrl}process-definition/key/${processDefinitionKey}/startForm`;
    return this.http.get<any>(url).pipe(
      tap(form => this.log(`fetched formkey`)),
      catchError(this.handleError('getProcessDeifnitionFormKey', []))
    );
  }

  getProcessDefinitions(): Observable<ProcessDefinition[]> {
    return this.http.get<ProcessDefinition[]>(this.engineRestUrl + 'process-definition?latestVersion=true').pipe(
      tap(processDefinitions => this.log(`fetched processDefinitions`)),
      catchError(this.handleError('getProcessDefinitions', []))
    );
  }

  deployProcess(fileToUpload: File): Observable<any> {
    const endpoint = `${this.engineRestUrl}deployment/create`;
    const formData = new FormData();

    formData.append('fileKey', fileToUpload, fileToUpload.name);

    return this.http.post(endpoint, formData);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    console.log(message);
  }
}
