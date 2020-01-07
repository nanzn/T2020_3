import { environment } from './../environments/environment.prod';
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


const baseUrl = "http://localhost:9090/api";

@Injectable({
  providedIn: 'root'
})

export class ApiRequestService {
  results: Object;

  constructor(private http: HttpClient) { }

  extractData(res: Response) {
    let body = res.json();
    return body || {};
  }

  getAllCustomer(apiName): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders().set("Access-Control-Allow-Origin", "*")
    };
    return this.http.get(baseUrl + '/' + apiName + '/all', httpOptions);
  }

  getCustomerName(apiName, username): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders().set("Access-Control-Allow-Origin", "*")
    };
    return this.http.get(baseUrl + '/' + apiName + '/getName/' + username, httpOptions);
  }

  getCustomerId(apiName, username): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders().set("Access-Control-Allow-Origin", "*")
    };
    return this.http.get(baseUrl + '/' + apiName + '/getCustomerId/' + username, httpOptions);
  }

  getAccountId(apiName, username): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders().set("Access-Control-Allow-Origin", "*")
    };
    return this.http.get(baseUrl + '/' + apiName + '/getAccountId/' + username, httpOptions);
  }

  getAccountDetail(apiName, username): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders().set("Access-Control-Allow-Origin", "*")
    };
    return this.http.get(baseUrl + '/' + apiName + '/getAccountDetails/' + username, httpOptions);
  }

  getTransaction(apiName, username): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders().set("Access-Control-Allow-Origin", "*")
    };
    return this.http.get(baseUrl + '/' + apiName + '/getTrans/' + username, httpOptions);
  }


  login(apiName, body): Observable<any> {
    return this.http.post(baseUrl + "/" + apiName + "/login", body, { headers: new HttpHeaders().set("Access-Control-Allow-Origin", "*"), responseType: 'text' });
  }

}
