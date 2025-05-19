import { Component, } from '@angular/core';
import { HttpClient  } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-web-page',
  imports: [CommonModule,FormsModule],
  templateUrl: './web-page.component.html',
  styleUrl: './web-page.component.css'
})
export class WebPageComponent {
  inputValue: number | null = null;
  result: string | null = null;
  time: number | null = null;
  error: string | null = null;

  constructor(private http: HttpClient) {}

  fetchLabSeq(): void {
    this.result = null;
    this.time = null;
    this.error = null;

    if (this.inputValue === null || this.inputValue < 0) {
      this.error = 'Please enter a valid non-negative integer.';
      return;
    }

    this.http.get<any>(`http://localhost:8080/labseq/${this.inputValue}`).subscribe({
      next: data => {
        this.result = data.result;
        this.time = data.time;
      },
      error: err => {
        this.error = 'Error fetching value: ' + (err.message || err.statusText);
      }
    });
  }
}
