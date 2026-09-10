import { TestBed } from '@angular/core/testing';
import { BrowserDynamicTestingModule, platformBrowserDynamicTesting } from '@angular/platform-browser-dynamic/testing';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

beforeAll(() => {
  TestBed.initTestEnvironment(
    BrowserDynamicTestingModule,
    platformBrowserDynamicTesting()
  );
});

beforeEach(() => {
  TestBed.resetTestingModule();
  TestBed.configureTestingModule({
    imports: [RouterTestingModule],
    providers: [
      provideHttpClientTesting()
    ]
  });
});
