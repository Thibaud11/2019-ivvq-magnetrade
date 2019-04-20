import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Proposal } from '../model/proposal';
import { Observable } from 'rxjs';

@Injectable()
export class ProposalService {

    private url: string;

    constructor(private http: HttpClient) {
        this.url = environment.api + '/proposal';
    }

    create(proposal: Proposal): Observable<Proposal> {
        return this.http.post<Proposal>(this.url + '/create', proposal);
    }

    edit(proposal: Proposal): Observable<Proposal> {
        return this.http.post<Proposal>(this.url + '/edit', proposal);
    }

    delete(id: number) {
        return this.http.delete(this.url + '/delete/' + id);
    }

    get(id: number): Observable<Proposal> {
        return this.http.get<Proposal>(this.url + '/' + id);
    }

    getAll(): Observable<Proposal[]> {
        return this.http.get<Proposal[]>(this.url + '/all');
    }

    getActives(): Observable<Proposal[]> {
        return this.http.get<Proposal[]>(this.url + '/active');
    }

}
