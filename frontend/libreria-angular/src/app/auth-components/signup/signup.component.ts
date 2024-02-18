import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/auth-services/auth-service/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  validateForm!: FormGroup;

  constructor(private service: AuthService, private fb: FormBuilder) {}

  ngOnInit() {
    // Initialize the form with validation rules
    this.validateForm = this.fb.group({
      name: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  // Method triggered on registration button click
  register() {
    console.log(this.validateForm.value);

    // Call the AuthService's signup method and subscribe to the response
    this.service.signup(this.validateForm.value).subscribe((res) => {
      console.log(res);
    });
  }
}
