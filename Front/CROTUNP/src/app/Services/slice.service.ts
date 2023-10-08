import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SliceService {
  constructor(private httpClient: HttpClient) { }

  readonly API_URL = 'http://localhost:8082/CROTUN/slice';

  getL(){
  return this.httpClient.get('http://localhost:8082/CROTUN/loan/retrieve-all-loans');
  }
  getAllslices() {
    return this.httpClient.get(`${this.API_URL}/retrieve-all-slices`);
  }
  addslice(idslice: any, slice: any) {
    return this.httpClient.post(` ${this.API_URL}/add-slice-lists/${idslice}`, slice);
  }
  editslice(slice: any) {
    return this.httpClient.put(`${this.API_URL}/edit-slice`, slice);
  }
  deleteslice(idslice: any) {
    return this.httpClient.delete(` ${this.API_URL}/delete-slice/${idslice}`);
  }
  acceptslice(idslice: any, slice: any) {
    return this.httpClient.put(` ${this.API_URL}/acceptsliceRequest/${idslice}`, slice);
  }
  denyslice(idslice: any, slice: any) {
    return this.httpClient.put(` ${this.API_URL}/denysliceRequest/${idslice}`, slice);
  }
  getslice(idslice: any) {
    return this.httpClient.get(`${this.API_URL}/retrieve-slice/${idslice}`);
  }
  exportToPDF() {
    return this.httpClient.get(`${this.API_URL}/export/pdf`);
  }
}
