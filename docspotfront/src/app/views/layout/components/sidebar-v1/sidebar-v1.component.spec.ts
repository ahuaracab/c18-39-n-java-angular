import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarV1Component } from './sidebar-v1.component';

describe('SidebarV1Component', () => {
  let component: SidebarV1Component;
  let fixture: ComponentFixture<SidebarV1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SidebarV1Component]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SidebarV1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
