import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LayoutV2Component } from './layout-v2.component';

describe('LayoutV2Component', () => {
  let component: LayoutV2Component;
  let fixture: ComponentFixture<LayoutV2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LayoutV2Component]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LayoutV2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
