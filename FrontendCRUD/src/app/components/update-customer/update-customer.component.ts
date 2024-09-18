import { Component } from '@angular/core';
import { CustomerService } from '../../service/customer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrl: './update-customer.component.css'
})
export class UpdateCustomerComponent {

  id!: number;
  updateCustomerForm!: FormGroup;

  constructor(private customerService: CustomerService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private router: Router
  ){}

  ngOnInit(){
    this.id = +this.activatedRoute.snapshot.params["id"];
    this.updateCustomerForm = this.fb.group({
      name: [null,[Validators.required]],
      email: [null,[Validators.required]],
      phone: [null,[Validators.email,Validators.required]]
    })
    this.getCustomerByid();
  }

  getCustomerByid(){
    this.customerService.getCustomerById(this.id).subscribe((res)=>{
      console.log(res);
      this.updateCustomerForm.patchValue(res);
    })
  }

  updateCutomer(){
    this.customerService.updateCustomer(this.id, this.updateCustomerForm.value).subscribe((res)=>{
      console.log(res);
      if(res.id != null){
      this.router.navigateByUrl("");
      }
    })
  }

}
