/*
 * Academic License - for use in teaching, academic research, and meeting
 * course requirements at degree granting institutions only.  Not for
 * government, commercial, or other organizational use.
 *
 * _coder_CDF_nowcast_api.h
 *
 * Code generation for function '_coder_CDF_nowcast_api'
 *
 */

#ifndef _CODER_CDF_NOWCAST_API_H
#define _CODER_CDF_NOWCAST_API_H

/* Include files */
#include "tmwtypes.h"
#include "mex.h"
#include "emlrt.h"
#include <stddef.h>
#include <stdlib.h>
#include "_coder_CDF_nowcast_api.h"

/* Variable Declarations */
extern emlrtCTX emlrtRootTLSGlobal;
extern emlrtContext emlrtContextGlobal;

/* Function Declarations */
extern real_T CDF_nowcast(real_T count);
extern void CDF_nowcast_api(const mxArray * const prhs[1], int32_T nlhs, const
  mxArray *plhs[1]);
extern void CDF_nowcast_atexit(void);
extern void CDF_nowcast_initialize(void);
extern void CDF_nowcast_terminate(void);
extern void CDF_nowcast_xil_terminate(void);

#endif

/* End of code generation (_coder_CDF_nowcast_api.h) */
