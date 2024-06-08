import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuV1Component } from './menu-v1.component';

describe('MenuV1Component', () => {
  let component: MenuV1Component;
  let fixture: ComponentFixture<MenuV1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MenuV1Component]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MenuV1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
