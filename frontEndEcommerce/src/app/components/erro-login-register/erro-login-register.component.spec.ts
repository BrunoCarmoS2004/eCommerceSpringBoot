import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErroLoginRegisterComponent } from './erro-login-register.component';

describe('ErroLoginRegisterComponent', () => {
  let component: ErroLoginRegisterComponent;
  let fixture: ComponentFixture<ErroLoginRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ErroLoginRegisterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ErroLoginRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
