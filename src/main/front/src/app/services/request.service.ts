import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';
import { Observable } from 'rxjs';
import { Request } from '../model/request';

@Injectable()
export class RequestService {

    private url: string;

    constructor(private http: HttpClient) {
        this.url = environment.api + '/request';
    }

    create(request: Request): Observable<Request> {
        return this.http.post<Request>(this.url + '/create', request);
    }

    edit(request: Request): Observable<Request> {
        return this.http.post<Request>(this.url + '/edit', request);
    }

    delete(id: number) {
        return this.http.delete(this.url + '/delete/' + id);
    }

    get(id: number): Observable<Request> {
        return this.http.get<Request>(this.url + '/' + id);
    }

    getAll(): Observable<Request[]> {
        return this.http.get<Request[]>(this.url + '/all');
    }

    getActives(): Observable<Request[]> {
        return this.http.get<Request[]>(this.url + '/active');
    }

}
