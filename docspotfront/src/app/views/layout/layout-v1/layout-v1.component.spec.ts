import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LayoutV1Component } from './layout-v1.component';

describe('LayoutV1Component', () => {
  let component: LayoutV1Component;
  let fixture: ComponentFixture<LayoutV1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LayoutV1Component]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LayoutV1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
